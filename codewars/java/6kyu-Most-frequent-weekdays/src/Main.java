import java.util.*;

import static java.util.Calendar.DAY_OF_YEAR;

/*
    What is your favourite day of the week? Check if it's the most frequent day of the week in the year.
    You are given a year as integer (e. g. 2001). You should return the most frequent day(s) of the week in that year. The result has to be a list of days sorted by the order of days in week (e. g. ['Monday', 'Tuesday']). Week starts with Monday.
    Input: Year as an int.
    Output: The list of most frequent days sorted by the order of days in week (from Monday to Sunday).
    Preconditions: Year is between 1 and 9999. Week starts with Monday. Calendar is Gregorian.
*/
public class Main {

    public static void main(String[] args) {
        String[] test = mostF(1084);
        for(int i = 0; i < test.length; i++){
            System.out.println(test[i]);
        }

    }
    public static String[] mostF(int year){
        Calendar calendar = Calendar.getInstance(Locale.US);
        calendar.set(year, Calendar.JANUARY, 1);
        int[] days = new int[8];
        int firstDay = convertDate(calendar.get(Calendar.DAY_OF_WEEK));
        Date date = calendar.getTime();
        for(int index = firstDay; index < days.length; index++){
            days[index]++;
        }
        calendar.set(year, Calendar.DECEMBER, 31);
        Date date2 = calendar.getTime();
        int lastDay = convertDate(calendar.get(Calendar.DAY_OF_WEEK));
        for(int index = 1; index <= lastDay; index++){
            days[index]++;
        }
        List<Day> result = new ArrayList<>();
        result.add(new Day("Monday", days[1]));
        result.add(new Day("Tuesday", days[2]));
        result.add(new Day("Wednesday", days[3]));
        result.add(new Day("Thursday", days[4]));
        result.add(new Day("Friday", days[5]));
        result.add(new Day("Saturday", days[6]));
        result.add(new Day("Sunday", days[7]));
        Collections.sort(result);
        final int max = result.get(0).value;
        return result.stream().filter(day -> day.value == max).map(day -> day.name).toArray(String[]::new);
    }

    public static int convertDate(int day){
        switch(day){
            case 1: return 7;
            default: return day-1;
        }
    }

    public static String[] mostFrequentDays(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, Calendar.JANUARY, 1);
        int[] days = new int[8];
        for(int i = 0; i < days.length; i++){
            days[i] = 0;
        }
        int daysInYear = calendar.getMaximum(DAY_OF_YEAR);
        for (int day = 0; day < daysInYear; day++) {
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
            days[dayOfWeek]++;
            calendar.add(DAY_OF_YEAR, 1);
        }
        List<Day> result = new ArrayList<>();
        result.add(new Day("Monday", days[2]));
        result.add(new Day("Tuesday", days[3]));
        result.add(new Day("Wednesday", days[4]));
        result.add(new Day("Thursday", days[5]));
        result.add(new Day("Friday", days[6]));
        result.add(new Day("Saturday", days[7]));
        result.add(new Day("Sunday", days[1]));

        Collections.sort(result);
        final int max = result.get(0).value;
        return result.stream().filter(day -> day.value == max).map(day -> day.name).toArray(String[]::new);
    }

    static class Day implements Comparable<Day> {
        public String name;
        public int value;

        public Day(String name, int value) {
            this.name = name;
            this.value = value;
        }

        @Override
        public int compareTo(Day o) {
            return o.value - this.value;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
