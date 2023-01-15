package com.example.aerotrackerapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import android.view.KeyEvent;
import android.view.View;

import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class Training extends AppCompatActivity {
    RadioButton individual, mixedPair, trio, group;
    RadioButton allCategories, allTrainingPeriods;
    RadioGroup radioGroupCategory, radioTrainingPeriod;

    private TextView eightCountsBP, partnershipBP, gPlusTransitionBP, elemntBP;
    private TextView eightCountsSP, partnershipSP, gPlusTransitionSP, elemntSP;
    private TextView viewBP, viewSP, viewEfficiency, viewIntensity;
    private EditText editMainPartStart, editMainPartEnd, editWarmUpStartTime, editWarmUpEndtTime;

    private Button btnDone;
    private String currentUserName = null;
    private String currentUserSurname = null;
    DataBaseHelper db;
    long id_ath, id_cat, id_period, id_hours;
    int id_prep;
    private ArrayList<TrainingPhysicalPreparation> trainingPhysicalPreparationArrayList;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);
        TextView helloAthlete = findViewById(R.id.helloAthlete);
        individual = (RadioButton) findViewById(R.id.rdbIW);
        mixedPair = (RadioButton) findViewById(R.id.rdbMP);
        trio = (RadioButton) findViewById(R.id.rdbTR);
        group = (RadioButton) findViewById(R.id.rdbGR);
        eightCountsBP = findViewById(R.id.valueBPeightCounts);
        partnershipBP = findViewById(R.id.valueBPpartnership);
        gPlusTransitionBP = findViewById(R.id.valueBPtransitionGplus);
        elemntBP = findViewById(R.id.valueBPelements);
        eightCountsSP = findViewById(R.id.valueSPeightCounts);
        partnershipSP = findViewById(R.id.valueSPpartnership);
        gPlusTransitionSP = findViewById(R.id.valueSPtransitionGplus);
        elemntSP = findViewById(R.id.valueSPelements);
        viewBP = findViewById(R.id.sumBP);
        viewSP = findViewById(R.id.sumSP);
        viewEfficiency = findViewById(R.id.view_efficiency);
        viewIntensity = findViewById(R.id.view_intensity);
        editMainPartStart = findViewById(R.id.timeMainPartStart);
        editMainPartEnd = findViewById(R.id.timeMainPartEnd);
        editWarmUpStartTime = findViewById(R.id.timeWarmUpStart);
        editWarmUpEndtTime = findViewById(R.id.timeWarmUpEnd);
        trainingPhysicalPreparationArrayList = new ArrayList<>();

        btnDone = findViewById(R.id.done_btn);

        db = new DataBaseHelper(this);
        Cursor cursor = db.getWritableDatabase().rawQuery("SELECT * FROM athlete", null);
        if (cursor.getCount() > 0) {
            currentUserName = getIntent().getStringExtra("name");
            currentUserSurname = getIntent().getStringExtra("surname");
            String hello = "Hello, " + currentUserName;
            helloAthlete.setText(hello);
        }
        cursor.close();

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (db.getAthlete(currentUserName, currentUserSurname) == 0) {
                    Toast.makeText(Training.this, "No such athlete  ",
                            Toast.LENGTH_SHORT).show();
                } else {
                    id_ath = db.getAthlete(currentUserName, currentUserSurname);
                }

                db.addNewResult(viewIntensity.getText().toString(), viewEfficiency.getText().toString(), id_ath, id_cat, id_period, id_hours, id_prep);
                Toast.makeText(Training.this, "Result has been added ",
                        Toast.LENGTH_SHORT).show();

                db.close();
                Intent i = new Intent(Training.this, PhysicalPreparation.class);
                i.putExtra("idPeriod", String.valueOf(id_period));
                i.putExtra("idHours", String.valueOf(id_hours));
                i.putExtra("name", currentUserName);
                i.putExtra("surname", currentUserSurname);
                i.putExtra("efficiency", viewEfficiency.getText().toString());
                i.putExtra("intensity", viewIntensity.getText().toString());
                i.putExtra("ws", editWarmUpStartTime.getText().toString());
                i.putExtra("we", editWarmUpEndtTime.getText().toString());
                i.putExtra("ms", editMainPartStart.getText().toString());
                i.putExtra("me", editMainPartEnd.getText().toString());
                i.putExtra("category", id_cat);
                i.putExtra("period", id_period);
                i.putExtra("prep", id_prep);
                i.putExtra("hours", id_hours);
                startActivity(i);
            }

        });
        editWarmUpEndtTime.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                String timeWS = editWarmUpStartTime.getText().toString();
                String timeWE = editWarmUpEndtTime.getText().toString();
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                String midnight = "24";

                try {
                    java.util.Date StartTimeWarmUp = sdf.parse(timeWS);
                    java.util.Date EndTimeWarmUP = sdf.parse(timeWE);

                    assert StartTimeWarmUp != null;
                    assert EndTimeWarmUP != null;

                    String[] first = StartTimeWarmUp.toString().split(" ");//1
                    String part = first[3];
                    String[] second = part.split(":");//2
                    String firstPartWS = second[0];
                    String secondPartWS = second[1];

                    String[] firstEnd = EndTimeWarmUP.toString().split(" ");//1
                    String partEnd = firstEnd[3];
                    String[] secondEnd = partEnd.split(":");//2
                    String firstPartWE = secondEnd[0];
                    String secondPartWE = second[1];

                    if (firstPartWS.equals("00")) {
                        String timeNewWS = midnight + ":" + secondPartWS;
                        java.util.Date midNumWS = sdf.parse(timeNewWS);
                        if (firstPartWE.equals("00")) {
                            String timeNewWE = midnight + ":" + secondPartWE;
                            java.util.Date midNumWE = sdf.parse(timeNewWE);
                            if (midNumWE.before(midNumWS)) {
                                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                                    Toast.makeText(Training.this, "Wrong time", Toast.LENGTH_SHORT).show();
                                    editWarmUpEndtTime.setText("");
                                }
                            }
                        } else if (EndTimeWarmUP.before(midNumWS)) {
                            if (actionId == EditorInfo.IME_ACTION_NEXT) {
                                Toast.makeText(Training.this, "Wrong time", Toast.LENGTH_SHORT).show();
                                editWarmUpEndtTime.setText("");
                            }
                        }
                    } else if (firstPartWE.equals("00")) {
                        String timeNewWE = midnight + ":" + secondPartWE;
                        java.util.Date midNumWE = sdf.parse(timeNewWE);
                        assert midNumWE != null;
                        if (midNumWE.before(StartTimeWarmUp)) {
                            if (actionId == EditorInfo.IME_ACTION_NEXT) {
                                Toast.makeText(Training.this, "Wrong time", Toast.LENGTH_SHORT).show();
                                editWarmUpEndtTime.setText("");
                            }
                        }

                    } else if (EndTimeWarmUP.before(StartTimeWarmUp)) {
                        if (actionId == EditorInfo.IME_ACTION_NEXT) {
                            Toast.makeText(Training.this, "Wrong time", Toast.LENGTH_SHORT).show();
                            editWarmUpEndtTime.setText("");
                        }
                    }


                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }


                return false;
            }
        });
        editMainPartStart.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                String timeWE = editWarmUpEndtTime.getText().toString();
                String timeMS = editMainPartStart.getText().toString();
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                String midnight = "24";

                //boolean b = false;
                try {
                    java.util.Date MainStartTime = sdf.parse(timeMS);
                    java.util.Date EndTimeWarmUp = sdf.parse(timeWE);

                    // Function to check whether a time is after an another time
                    assert MainStartTime != null;
                    assert EndTimeWarmUp != null;
                    String[] first = EndTimeWarmUp.toString().split(" ");
                    String part = first[3]; //първи сплит
                    String[] second = part.split(":"); //втори сплит
                    String firstPartWE = second[0];
                    String secondPartWE = second[1];
                    String[] firstStartMS = MainStartTime.toString().split(" ");// първи сплит
                    String partStart = firstStartMS[3];
                    String[] secondStart = partStart.split(":"); // втори сплит
                    String firstPartMS = secondStart[0];
                    String secondPartMS = secondStart[1];

                    if (firstPartWE.equals("00")) {
                        String TimeNewWE = midnight + ":" + secondPartWE;
                        java.util.Date midNumWE = sdf.parse(TimeNewWE);
                        if (firstPartMS.equals("00")) {
                            String TimeNewMS = midnight + ":" + secondPartMS;
                            java.util.Date midNumMS = sdf.parse(TimeNewMS);
                            assert midNumMS != null;
                            if (midNumMS.before(midNumWE)) {
                                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                                    Toast.makeText(Training.this, "Wrong time", Toast.LENGTH_SHORT).show();
                                    editMainPartStart.setText("");
                                }
                            }
                        } else if (MainStartTime.before(midNumWE)) {
                            if (actionId == EditorInfo.IME_ACTION_NEXT) {
                                Toast.makeText(Training.this, "Wrong time", Toast.LENGTH_SHORT).show();
                                editMainPartStart.setText("");
                            }
                        }


                    } else if (firstPartMS.equals("00")) {
                        String TimeNewMS = midnight + ":" + secondPartMS;
                        java.util.Date midNumMS = sdf.parse(TimeNewMS);
                        assert midNumMS != null;
                        if (midNumMS.before(EndTimeWarmUp)) {
                            if (actionId == EditorInfo.IME_ACTION_NEXT) {
                                Toast.makeText(Training.this, "Wrong time", Toast.LENGTH_SHORT).show();
                                editMainPartStart.setText("");
                            }
                        }

                    } else if (MainStartTime.before(EndTimeWarmUp)) {
                        if (actionId == EditorInfo.IME_ACTION_NEXT) {
                            Toast.makeText(Training.this, "Wrong time", Toast.LENGTH_SHORT).show();
                            editMainPartStart.setText("");
                        }
                    }


                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }


                return false;
            }
        });


        editMainPartEnd.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                id_ath = db.getAthlete(currentUserName, currentUserSurname);
                String timeMS = editMainPartStart.getText().toString();
                String timeME = editMainPartEnd.getText().toString();
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                String midnight = "24";

                //boolean b = false;
                try {
                    java.util.Date MainStartTime = sdf.parse(timeMS);
                    java.util.Date MainEndTime = sdf.parse(timeME);

                    // Function to check whether a time is after an another time
                    assert MainStartTime != null;
                    assert MainEndTime != null;
                    String[] first = MainStartTime.toString().split(" "); //първи сплит на MS
                    String part = first[3];
                    String[] second = part.split(":"); // втори сплит на MS
                    String firstPartMS = second[0];
                    String secondPartMS = second[1];

                    String[] firstEnd = MainEndTime.toString().split(" "); //първи сплит на ME
                    String partEnd = firstEnd[3];
                    String[] secondEnd = partEnd.split(":"); // втори сплит на ME
                    String firstPartME = secondEnd[0];
                    String secondPartME = secondEnd[1];

                    if (firstPartMS.equals("00")) {
                        String timeNewMS = midnight + ":" + secondPartMS;
                        java.util.Date midNumMS = sdf.parse(timeNewMS);
                        if (firstPartME.equals("00")) {
                            String timeNewME = midnight + ":" + secondPartME;
                            java.util.Date midNumME = sdf.parse(timeNewME);
                            assert midNumME != null;
                            if (midNumME.before(midNumMS)) {
                                if (actionId == EditorInfo.IME_ACTION_DONE) {
                                    Toast.makeText(Training.this, "Wrong time", Toast.LENGTH_SHORT).show();
                                    editMainPartEnd.setText("");
                                }
                            }
                        } else if (MainEndTime.before(midNumMS)) {
                            if (actionId == EditorInfo.IME_ACTION_DONE) {
                                Toast.makeText(Training.this, "Wrong time", Toast.LENGTH_SHORT).show();
                                editMainPartEnd.setText("");
                            }
                        }

                    } else if (firstPartME.equals("00")) {
                        String timeNewME = midnight + ":" + secondPartME;
                        java.util.Date midNumME = sdf.parse(timeNewME);
                        if (midNumME.before(MainStartTime)) {
                            if (actionId == EditorInfo.IME_ACTION_DONE) {
                                Toast.makeText(Training.this, "Wrong time", Toast.LENGTH_SHORT).show();
                                editMainPartEnd.setText("");
                            }
                        }

                    } else if (MainEndTime.before(MainStartTime)) {
                        if (actionId == EditorInfo.IME_ACTION_DONE) {
                            Toast.makeText(Training.this, "Wrong time", Toast.LENGTH_SHORT).show();
                            editMainPartEnd.setText("");
                        }
                    } else {
                        if (actionId == EditorInfo.IME_ACTION_DONE) {

                            boolean insertingTime = db.addTimeIntoTrainingTable(editWarmUpStartTime.getText().toString(), editWarmUpEndtTime.getText().toString(), editMainPartStart.getText().toString(), editMainPartEnd.getText().toString(), id_ath);

                            if (insertingTime == true) {
                                id_hours = db.getHoursId(editWarmUpStartTime.getText().toString(), editWarmUpEndtTime.getText().toString(), editMainPartStart.getText().toString(), editMainPartEnd.getText().toString());
                                Toast.makeText(Training.this, "Time inserted", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(Training.this, "Failed to insert", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }


                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                return false;
            }
        });


    }


    @SuppressLint("NonConstantResourceId")
    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        String str = "";
        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.rdbIW:
                if (checked)
                    str = "Individual selected";
                break;
            case R.id.rdbMP:
                if (checked)
                    str = "Mixed pair Selected";
                break;
            case R.id.rdbTR:
                if (checked)
                    str = "Trio Selected";
                break;
            case R.id.rdbGR:
                if (checked)
                    str = "Group Selected";
                break;
        }
        Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();

        radioGroupCategory = (RadioGroup) findViewById(R.id.rdGroup);
        int selectedId = radioGroupCategory.getCheckedRadioButtonId();
        allCategories = (RadioButton) findViewById(selectedId);
        String category = allCategories.getText().toString();


        if (str.equals("Individual selected")) {
            if (category.equals("Individual")) {
                id_cat = db.getCategory(category);

            }
        } else if (str.equals("Mixed pair Selected")) {
            if (category.equals("Mixed pair")) {
                id_cat = db.getCategory(category);

            }
        } else if (str.equals("Trio Selected")) {
            if (category.equals("Trio")) {
                id_cat = db.getCategory(category);

            }
        } else if (str.equals("Group Selected")) {
            if (category.equals("Group")) {
                id_cat = db.getCategory(category);

            }
        }

    }

    public void onRadioButtonClickedTrainingPeriod(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        String str = "";
        radioTrainingPeriod = (RadioGroup) findViewById(R.id.rdTrainingPeriod);
        int selectedId = radioTrainingPeriod.getCheckedRadioButtonId();
        allTrainingPeriods = (RadioButton) findViewById(selectedId);
        String period = allTrainingPeriods.getText().toString();
        trainingPhysicalPreparationArrayList = db.readPhysicalPreparation();
        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.rdbPreparatory:
                if (checked)
                    str = "Perparatory period selected";
                id_period = db.getPeriod(period);
                if (trainingPhysicalPreparationArrayList.size() != 0) {
                    TrainingPhysicalPreparation row1 = trainingPhysicalPreparationArrayList.get(trainingPhysicalPreparationArrayList.size() - 1);
                    id_prep = Integer.parseInt(row1.getId());
                }

                break;
            case R.id.rdbPrecompetitive:
                if (checked)
                    str = "Precompetitive period selected";
                id_period = db.getPeriod(period);
                if (trainingPhysicalPreparationArrayList.size() != 0) {
                    TrainingPhysicalPreparation row2 = trainingPhysicalPreparationArrayList.get(trainingPhysicalPreparationArrayList.size() - 1);
                    id_prep = Integer.parseInt(row2.getId());
                }
                break;
            case R.id.rdbCompetitive:
                if (checked)
                    str = "Competitive period selected";
                id_period = db.getPeriod(period);
                if (trainingPhysicalPreparationArrayList.size() != 0) {
                    TrainingPhysicalPreparation row3 = trainingPhysicalPreparationArrayList.get(trainingPhysicalPreparationArrayList.size() - 1);
                    id_prep = Integer.parseInt(row3.getId());
                }
                break;
            case R.id.rdbPostcompetitive:
                if (checked)
                    str = "Postcompetitive period Selected";
                id_period = db.getPeriod(period);
                if (trainingPhysicalPreparationArrayList.size() != 0) {
                    TrainingPhysicalPreparation row4 = trainingPhysicalPreparationArrayList.get(trainingPhysicalPreparationArrayList.size() - 1);
                    id_prep = Integer.parseInt(row4.getId());
                }
                break;
        }
        Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();


    }


    public void IncreaseEightCountBP(View view) { //BP
        if (Integer.parseInt(eightCountsBP.getText().toString()) >= 0) {
            int increaseCount = Integer.parseInt(eightCountsBP.getText().toString()) + 1;
            eightCountsBP.setText(String.valueOf(increaseCount));
        }

    }

    public void DecreaseEightCountBP(View view) { //BP
        if (Integer.parseInt(eightCountsBP.getText().toString()) >= 1) {
            int decreaseCount = Integer.parseInt(eightCountsBP.getText().toString()) - 1;
            eightCountsBP.setText(String.valueOf(decreaseCount));
        }
    }

    public void IncreasePartnershipBP(View view) { //BP
        if (Integer.parseInt(partnershipBP.getText().toString()) >= 0) {
            int increaseCount = Integer.parseInt(partnershipBP.getText().toString()) + 1;
            partnershipBP.setText(String.valueOf(increaseCount));
        }
    }

    public void DecreasePartnershipBP(View view) { //BP
        if (Integer.parseInt(partnershipBP.getText().toString()) >= 1) {
            int decreaseCount = Integer.parseInt(partnershipBP.getText().toString()) - 1;
            partnershipBP.setText(String.valueOf(decreaseCount));
        }
    }

    public void IncreaseGplusBP(View view) { //BP
        if (Integer.parseInt(gPlusTransitionBP.getText().toString()) >= 0) {
            int increaseCount = Integer.parseInt(gPlusTransitionBP.getText().toString()) + 1;
            gPlusTransitionBP.setText(String.valueOf(increaseCount));
        }
    }

    public void DecreaseGplusBP(View view) { //BP
        if (Integer.parseInt(gPlusTransitionBP.getText().toString()) >= 1) {
            int increaseCount = Integer.parseInt(gPlusTransitionBP.getText().toString()) - 1;
            gPlusTransitionBP.setText(String.valueOf(increaseCount));
        }
    }

    public void IncreaseElementBP(View view) { //BP
        if (Integer.parseInt(elemntBP.getText().toString()) >= 0) {
            int increaseCount = Integer.parseInt(elemntBP.getText().toString()) + 1;
            elemntBP.setText(String.valueOf(increaseCount));
        }
    }

    public void DecreaseElementBP(View view) { //BP
        if (Integer.parseInt(elemntBP.getText().toString()) >= 1) {
            int increaseCount = Integer.parseInt(elemntBP.getText().toString()) - 1;
            elemntBP.setText(String.valueOf(increaseCount));
        }
    }

    public void IncreaseEightCountSP(View view) {
        if (Integer.parseInt(eightCountsSP.getText().toString()) >= 0) {
            int increaseCount = Integer.parseInt(eightCountsSP.getText().toString()) + 1;
            eightCountsSP.setText(String.valueOf(increaseCount));
        }

    }

    public void DecreaseEightCountSP(View view) {
        if (Integer.parseInt(eightCountsSP.getText().toString()) >= 1) {
            int decreaseCount = Integer.parseInt(eightCountsSP.getText().toString()) - 1;
            eightCountsSP.setText(String.valueOf(decreaseCount));
        }
    }

    public void IncreasePartnershipSP(View view) {
        if (Integer.parseInt(partnershipSP.getText().toString()) >= 0) {
            int increaseCount = Integer.parseInt(partnershipSP.getText().toString()) + 1;
            partnershipSP.setText(String.valueOf(increaseCount));
        }
    }

    public void DecreasePartnershipSP(View view) {
        if (Integer.parseInt(partnershipSP.getText().toString()) >= 1) {
            int decreaseCount = Integer.parseInt(partnershipSP.getText().toString()) - 1;
            partnershipSP.setText(String.valueOf(decreaseCount));
        }
    }

    public void IncreaseGplusSP(View view) {
        if (Integer.parseInt(gPlusTransitionSP.getText().toString()) >= 0) {
            int increaseCount = Integer.parseInt(gPlusTransitionSP.getText().toString()) + 1;
            gPlusTransitionSP.setText(String.valueOf(increaseCount));
        }
    }

    public void DecreaseGplusSP(View view) {
        if (Integer.parseInt(gPlusTransitionSP.getText().toString()) >= 1) {
            int increaseCount = Integer.parseInt(gPlusTransitionSP.getText().toString()) - 1;
            gPlusTransitionSP.setText(String.valueOf(increaseCount));
        }
    }

    public void IncreaseElementSP(View view) {
        if (Integer.parseInt(elemntSP.getText().toString()) >= 0) {
            int increaseCount = Integer.parseInt(elemntSP.getText().toString()) + 1;
            elemntSP.setText(String.valueOf(increaseCount));
        }
    }

    public void DecreaseElementSP(View view) {
        if (Integer.parseInt(elemntSP.getText().toString()) >= 1) {
            int increaseCount = Integer.parseInt(elemntSP.getText().toString()) - 1;
            elemntSP.setText(String.valueOf(increaseCount));
        }
    }


    public void sumOfBigParts(View view) {
        int get_eightCounts = Integer.parseInt(eightCountsBP.getText().toString());
        int get_gtransition = Integer.parseInt(partnershipBP.getText().toString()) * 2;
        int get_gPlusTransition = (Integer.parseInt(gPlusTransitionBP.getText().toString()) * 2);
        int get_elements = Integer.parseInt(elemntBP.getText().toString());
        int resultBigParts = get_elements + get_gPlusTransition + get_gtransition + get_eightCounts;
        viewBP.setText(String.valueOf(resultBigParts));
        // int sumOfBigParts = Integer.parseInt(viewBP.getText().toString());
       /* Boolean result = db.insertBigParts(resultBigParts);
        if (result == true) {
            Toast.makeText(Training.this, "Successfully added to big parts.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(Training.this, "Failed to add.", Toast.LENGTH_SHORT).show();
        }*/
    }

    public void sumOfSmallParts(View view) {
        int get_eightCounts = Integer.parseInt(eightCountsSP.getText().toString());
        int get_gtransition = Integer.parseInt(partnershipSP.getText().toString()) * 2;
        int get_gPlusTransition = (Integer.parseInt(gPlusTransitionSP.getText().toString()) * 2);
        int get_elements = Integer.parseInt(elemntSP.getText().toString());
        int resultSmallParts = get_elements + get_gPlusTransition + get_gtransition + get_eightCounts;
        viewSP.setText(String.valueOf(resultSmallParts));
       /* Boolean result = db.insertSmallParts(resultSmallParts);
        if (result == true) {
            Toast.makeText(Training.this, "Successfully added to small parts.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(Training.this, "Failed to add.", Toast.LENGTH_SHORT).show();
        }*/
    }

    public void calculateTrainingIntensity(View view) throws ParseException {
        //big+small parts/ main part time (трябва да изчисля времето)
        // в коефициент 164/85=1,9 коеф

        double sumBPandSP = Double.parseDouble(viewSP.getText().toString()) + Double.parseDouble(viewBP.getText().toString());
        String timeStart = editMainPartStart.getText().toString();
        String timeEnd = editMainPartEnd.getText().toString();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");

        Date date1 = simpleDateFormat.parse(timeStart);
        Date date2 = simpleDateFormat.parse(timeEnd);
        assert date2 != null;
        assert date1 != null;

        long diff = date2.getTime() - date1.getTime();
        long diffMinutes = diff / (60 * 1000);
        double time = Double.parseDouble(String.valueOf(diffMinutes));
        double result = sumBPandSP / time;
        viewIntensity.setText(String.format(Locale.US, "%.2f", result));

    }

    public void calculateTrainingEfficiency(View view) {
        //big parts/ sum of small+big parts == % (122/164)*100 = 74%

        double sumBPandSP = Double.parseDouble(viewSP.getText().toString()) + Double.parseDouble(viewBP.getText().toString());
        double bigPartsSum = Double.parseDouble(viewBP.getText().toString());
        double result = (bigPartsSum / sumBPandSP) * 100;
        viewEfficiency.setText(String.format(Locale.US, "%.0f%%", result));

    }


}
