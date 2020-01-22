package at.htlkaindorf.linhard_plf;

import android.util.Log;

import java.security.Key;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class HappinessModel {
    private Map<String, List<Integer>> persons;

    public HappinessModel() {
        this.persons = new HashMap<>();
        this.generateTestValues();
    }

    private void generateTestValues() {
        Random r = new Random();

        int[] amountPerPerson = new int[4];
        for (int i = 0; i < 3; i++) {
            amountPerPerson[i] = r.nextInt(6) + 1;
        }
        int sum = 0;
        for (int i = 0; i < 3; i++) {
            sum += amountPerPerson[i];
        }
        int remainder = 20 - sum;
        amountPerPerson[3] = remainder;

        List<Integer> listMarge = new ArrayList<>();
        for (int i = 0; i < amountPerPerson[0]; i++) {
            listMarge.add(r.nextInt(11) + 1);
        }

        List<Integer> listHomer = new ArrayList<>();
        for (int i = 0; i < amountPerPerson[1]; i++) {
            listHomer.add(r.nextInt(11) + 1);
        }

        List<Integer> listBart = new ArrayList<>();
        for (int i = 0; i < amountPerPerson[2]; i++) {
            listBart.add(r.nextInt(11) + 1);
        }

        List<Integer> listLisa = new ArrayList<>();
        for (int i = 0; i < amountPerPerson[3]; i++) {
            listLisa.add(r.nextInt(11) + 1);
        }

        persons.put("Marge", listMarge);
        persons.put("Homer", listHomer);
        persons.put("Bart", listBart);
        persons.put("Lisa", listLisa);

    }

    public void addHappinessValue(String name, final int happiness) {
        if (this.persons.get(name) == null) {
            this.persons.put(name, new ArrayList<Integer>() {{
                add(happiness);
            }});
        } else {
            List<Integer> currValues = this.persons.get(name);
            currValues.add(happiness);
            this.persons.replace(name, currValues);
        }
    }

    public String getTopThreeString() {
        Map<String, Double> values = new HashMap<>();

        for (String s : persons.keySet()) {
            values.put(s, getAverageForName(s));
        }

        for(Double d : values.values()) {
            Log.i(MainActivity.class.getSimpleName(), d + "\n");
        }

        Map<String, Double> newValues = new HashMap<>();
        for (Map.Entry<String, Double> val : values.entrySet()) {
            String secondName = "";

            for (Map.Entry<String, Double> secondVal : values.entrySet()) {
                if (secondVal.getValue() == val.getValue() && !secondVal.getKey().equals(val.getKey())) {
                    //found person with same scores
                    secondName = "," + secondVal.getKey();
                }
            }

            newValues.put(val.getKey() + secondName, val.getValue());
            Log.i(MainActivity.class.getSimpleName(), secondName);
        }

        for(Map.Entry<String, Double> val : newValues.entrySet()) {
            Log.i(MainActivity.class.getSimpleName(), val.getValue() + "");
        }

        Map<Double, String> newMap = new HashMap<>();
        for (Map.Entry<String, Double> val : newValues.entrySet()) {
            newMap.put(val.getValue(), val.getKey());
        }
        //newvalues now contains the persons with same avg (if any)
        List<String> highest = getHighestAverageHappiness(newMap);

        String result = "";
        for (String s : highest) {
            result += (String.format("%.2f", newValues.get(s)) + " - " + s + "\n");
        }
        return result;
    }

    private List<String> getHighestAverageHappiness(Map<Double, String> scores) {
        List<Double> sortedScores = new ArrayList<>();
        for (Map.Entry<Double, String> val : scores.entrySet()) {
            sortedScores.add(val.getKey());
        }
        sortedScores.sort(new Comparator<Double>() {
            @Override
            public int compare(Double o1, Double o2) {
                return (int) Math.round(o2 - o1);
            }
        });

        List<String> result = new ArrayList<>();
        for (int i = 0; i < sortedScores.size(); i++) {
            result.add(scores.get(sortedScores.get(i)));
        }
        return result;
    }

    private int getHappinessForValues(List<Integer> values) {
        int sum = 0;
        for (int val : values) {
            sum += val;
        }
        return sum;
    }

    private double getAverageForName(String name) {
        List<Integer> values = persons.get(name);
        return (getHappinessForValues(values) / values.size());
    }
}
