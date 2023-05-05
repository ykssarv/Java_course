package ee.taltech.iti0202.exam;

    public class Exam {

        public static final int MINUTES_IN_HOUR = 60;
        public static final int HOURS_IN_DAY = 24;

        /**
         * Write a  method that finds correct difference between two timestamps. Timestamps are given in format HH:MM
         * HH is two-digit hour marker and MM is two-digit
         * minutes marker. Result must be also in format HH:MM. Difference means basically "time2" minus "time1".
         * timeDiff("10:00", "10:00") => "00:00"
         * timeDiff("10:00", "11:01") => "01:01"
         * timeDiff("10:00", "09:01") => "23:01"
         * timeDiff("10:00", "08:59") => "22:59"
         * timeDiff("10:01", "10:00") => "23:59"
         *
         * @param time1 time as HH:MM
         * @param time2 time as HH:MM
         * @return time difference as HH:MM
         */
        public static String timeDiff(String time1, String time2) {
            String[] splitTime1 = time1.split(":");
            String[] splitTime2 = time2.split(":");

            int hours1 = Integer.parseInt(splitTime1[0]);
            int hours2 = Integer.parseInt(splitTime2[0]);

            int minutes1 = Integer.parseInt(splitTime1[1]);
            int minutes2 = Integer.parseInt(splitTime2[1]);

            int total1 = hours1 * MINUTES_IN_HOUR + minutes1;
            int total2 = hours2 * MINUTES_IN_HOUR + minutes2;

            int diff = total2 - total1;

            if (diff < 0) {
                diff += MINUTES_IN_HOUR * HOURS_IN_DAY;
            }



            int hours = diff / MINUTES_IN_HOUR;
            int minutes = diff % MINUTES_IN_HOUR;

            String hoursString = (hours < 10 ? "0" : "") + hours;
            String minutesString = (minutes < 10 ? "0" : "") + minutes;
            return hoursString + ":" + minutesString;
        }

        /**
         * Given a string, encode the string using Run-length encoding.
         * RLE is a form of data compression, where runs (consecutive data elements)
         * are replaced by just one data value and count.
         * <p>
         * encode("TalTech") => "Taltech"
         * encode("TTU") => "2TU"
         * encode("WWWABBBA") => "3WA3BA"
         * encode("  ") => "2 "
         *
         * @param data string to encode
         * @return encoded string
         */
        public static String encode(String data) {
            String symbol = null;
            int symbolCount = 0;
            String answer = "";

            for (String chara : data.split("")) {
                if (symbol == null) {
                    symbol = chara;
                    symbolCount += 1;
                    continue;
                }
                if (symbol.equals(chara)) {
                    symbolCount += 1;
                    continue;
                }
                answer = answer + (symbolCount > 1 ? symbolCount : "") + symbol;
                symbol = chara;
                symbolCount = 1;

            }
            return answer + (symbolCount > 1 ? symbolCount : "") + symbol;
        }

    }

