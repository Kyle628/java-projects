// Kyle O'Connor kyjoconn@ucsc.edu
import java.util.GregorianCalendar;
import java.util.Calendar;
import java.util.*;
import static java.lang.System.*;

class jcal {
    static final GregorianCalendar CHANGE_DATE
        = new GregorianCalendar(1752, 8, 14);
    static final int MONTHS_IN_YEAR = 12;
    static final int WEEKS_IN_MONTH = 6;
    static final int DAYS_IN_WEEK   = 7;

    public static void main(String[] args) {
        if (args.length == 0) {
            Locale locale = new Locale("us");
            print_this_month(locale);
            System.exit(0);
        }
        if (args.length == 1) {
            boolean flag = check_for_loc(args[0]);
            if (flag == false) {
                int calyear = comm_line_1(args[0]);
                Locale locale = new Locale("us");
                print_year(make_year(calyear), calyear, locale);
            } else {
                Locale locale = new Locale(args[0].substring(1));
                print_this_month(locale);
            }
        }
        if (args.length == 2) {
            boolean flag = check_for_loc(args[0]);
            if (flag == false) {
                Locale locale = new Locale("us");
                int[] command_line = comm_line(args[0], args[1]);
                int calmonth = command_line[0];
                int calyear = command_line[1];
                int[][] month = make_month(calmonth, calyear);
                print_month(month, calmonth, calyear, locale);
            } else {
                Locale locale = new Locale(args[0].substring(1));
                int calyear = comm_line_1(args[1]);
                print_year(make_year(calyear), calyear, locale);
            }
        }
        if (args.length == 3) {
            Locale locale = new Locale(args[0].substring(1));
            int[] command_line = comm_line(args[1], args[2]);
            int calmonth = command_line[0];
            int calyear = command_line[1];
            int[][] month = make_month(calmonth, calyear);
            print_month(month, calmonth, calyear, locale);
        }           
    }
        
    static int[][] make_month (int calmonth, int calyear) {
        int[][] month = new int[WEEKS_IN_MONTH][DAYS_IN_WEEK];
        GregorianCalendar cal = new GregorianCalendar();
        cal.setGregorianChange(CHANGE_DATE.getTime());
        calmonth -= 1;
        cal.set(calyear, calmonth, 1);
        int weeknum = 0;
        while (calmonth == cal.get(GregorianCalendar.MONTH)) {
            int weekday = cal.get(GregorianCalendar.DAY_OF_WEEK)-1;
            int calday = cal.get(GregorianCalendar.DAY_OF_MONTH);
            month[weeknum][weekday] = calday;
            cal.add(GregorianCalendar.DAY_OF_MONTH, 1);
            if (weekday == 6)
                weeknum += 1;
        }
        return month;
    }
    static void print_month(int[][] month, int calmonth, int calyear,
                            Locale locale) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MONTH, calmonth - 1);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        out.printf("%3s %s %d", " ", cal.getDisplayName(Calendar.MONTH,
                  Calendar.LONG, locale), calyear);
        out.printf("%n");
        int weekcount = 0;
        print_week_days_loc(locale);
        out.printf("%n");
            for (int[] week: month) {
                weekcount += 1;
                for (int day = 0; day < week.length; ++day) {
                    if (week[day] == 0)
                        out.printf(day == 0 ? "%2s" : "%3s", " " ); 
                    else
                        out.printf(
                        day == 0 ? "%2d" : "%3d", week[day]);
                }
            out.printf ("%n");
            }       
     }
    static void print_this_month(Locale locale) {
        GregorianCalendar c = new GregorianCalendar();
        int thismonth = Integer.valueOf(c.get(GregorianCalendar.MONTH));
        int thisyear = Integer.valueOf(c.get(GregorianCalendar.YEAR));
        int[][] month = make_month(thismonth + 1, thisyear);
        print_month(month, thismonth + 1, thisyear, locale);
    }
    static int[] comm_line(String strmonth, String stryear) {
        int[] command_line = new int[2];
        try {
            command_line[0] = Integer.parseInt(strmonth);
        } catch (Exception e) {
            misclib.die("illegal month value: use 1-12");
        }
        if (command_line[0] > 12 || command_line[0] < 1)
            misclib.die("illegal month value: use 1-12");
        try {
            command_line[1] = Integer.parseInt(stryear);
        } catch (Exception e) {
            misclib.die("illegal year value: use 1-9999");
        }
        if (command_line[1] > 9999 || command_line[1] < 1)
            misclib.die("illegal year value: use 1-9999");
        return command_line;
    }
    static int comm_line_1(String stryear) {
        int year = 0;
        try {
            year = Integer.parseInt(stryear);
        } catch (Exception e) {
            misclib.die("illegal year value: use 1-9999");
        }
        if (year > 9999 || year < 1)
            misclib.die("illegal year value: use 1-9999");
        return year;
    }
    static int[][][] make_year(int calyear) {
        int[][][] year = new 
                  int[MONTHS_IN_YEAR][WEEKS_IN_MONTH][DAYS_IN_WEEK];
        GregorianCalendar yearcal = new GregorianCalendar();
        yearcal.set(calyear, 0, 1);
        int month = 0;
        while (calyear == yearcal.get(GregorianCalendar.YEAR)) {
                year[month] = make_month(month + 1, calyear);
                yearcal.add(GregorianCalendar.MONTH, 1);
                month += 1;
            }
        return year;
    }
    static void print_year(int[][][] year, int calyear, Locale locale) {
        out.printf("%n");
        out.printf("%35s %4d", " ", calyear);
        out.printf("%n%n");
        GregorianCalendar cal = new GregorianCalendar(locale);
        cal.set(GregorianCalendar.MONTH, GregorianCalendar.JANUARY);
        cal.set(GregorianCalendar.DAY_OF_MONTH, 1);
        for (int i = 0; i < 10; i += 3) {
            long month = i;
            int millsecinday = 24*60*60*1000;
            long date1 = ((month)*30 + 15)*millsecinday;
            long date2 = ((month + 1)*30 + 15)*millsecinday;
            long date3 = ((month + 2)*30 + 15)*millsecinday;
            out.printf(locale, "%10tB %30tB %20tB",
                       date1, date2, date3);
            out.printf("%n");
            print_week_days();
            out.printf("%n");
            for (int j = 0; j < 6; j++) {
                print_week(year[i][j]);
                out.print("   ");
                print_week(year[i + 1][j]);
                out.print("   ");
                print_week(year[i + 2][j]);
                out.printf("%n");
            }
        }
    }
    static void print_week(int[] week) {
        for (int i = 0; i < 7; i++) {
            if (week[i] == 0)
                out.printf(i == 0 ? "%2s" : "%3s", " " );
            else
                out.printf(i == 0 ? "%2d" : "%3d", week[i]);
        }
    }
    static void print_week_days() {
        out.print("Su"+" "+"Mo"+" "+"Tu"+" "+"We"+" "+
                  "Th"+" "+"Fr"+" "+"Sa");
        out.print("   ");
        out.print("Su"+" "+"Mo"+" "+"Tu"+" "+"We"+" "+
                  "Th"+" "+"Fr"+" "+"Sa");
        out.print("   ");
        out.print("Su"+" "+"Mo"+" "+"Tu"+" "+"We"+" "+
                  "Th"+" "+"Fr"+" "+"Sa");
    }
    static void print_week_days_loc(Locale locale) {
        GregorianCalendar cal = new GregorianCalendar(locale);
        cal.set(GregorianCalendar.MONTH, GregorianCalendar.JANUARY);
        cal.set(GregorianCalendar.DAY_OF_MONTH, 1);
        cal.add(GregorianCalendar.DAY_OF_MONTH,
                (8 - cal.get(GregorianCalendar.DAY_OF_WEEK)) % 7);
        do {
            String dayname = String.format(locale, "%tA", cal);
            out.printf(locale, "%s%s", prefix(dayname), " ");
            cal.add(GregorianCalendar.DAY_OF_MONTH, 1);
        }while (cal.get(GregorianCalendar.DAY_OF_WEEK) !=
                GregorianCalendar.SUNDAY);
    }
    static String prefix(String string) {
        return string.substring(0, 2);
    }
    static boolean check_for_loc(String str) {
        boolean flag = false;
        try {
            int year = Integer.parseInt(str);
            return flag;
        } catch (Exception e) {
            flag = true;
            return flag;
        }           
    }
}

    
