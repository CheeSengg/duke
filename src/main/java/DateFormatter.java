public class DateFormatter {
    private String date;
    private String time;

    /**
     *
     * @param message the deadline or event time to be processed to give date and time params
     */
    DateFormatter(String message){
        String[] splitStr = message.split(" ");

        date = formatDate(splitStr[0]);
        time = formatTime(splitStr[1]);
    }

    /**
     *
     * @param date the date as per input by user
     * @return String new format for the date, null if date does not exist
     */
    private String formatDate(String date){
        String[] splitDate = date.split("/");
        String day = dayFormat(splitDate[0]);
        String month = monthFormat(splitDate[1]);
        String year = splitDate[2];

        if(month == null || day == null || !isValidDate(splitDate[0], splitDate[1], splitDate[2]))
            return null;

        return day + month + year + ", ";
    }

    /**
     *
     * @param day the date to be appended with necessary suffix
     * @return String of the day with its suffix
     */
    private String dayFormat(String day){
        if(day.equals("1") || day.equals("21") || day.equals("31")){
            day = day + "st of ";
        } else if(day.equals("2") || day.equals("22")){
            return day + "nd of ";
        } else if(day.equals("3") || day.equals("23")){
            return day + "rd of ";
        } else if (Integer.parseInt(day) > 31){
            return null;
        }

        return day + "th of ";
    }

    /**
     *
     * @param month the month to be converted
     * @return the String format of month
     */
    private String monthFormat(String month){
        switch (month){
            case "1":
                return "January ";
            case "2":
                return"February ";
            case "3":
                return "March ";
            case "4":
                return "April ";
            case "5":
                return "May ";
            case "6":
                return "June ";
            case "7":
                return "July ";
            case "8":
                return "August ";
            case "9":
                return "September ";
            case "10":
                return "October ";
            case "11":
                return "November ";
            case "12":
                return "December ";
            default:
                return null;
        }
    }

    /**
     *
     * @param day to be converted to int to check if day is in range for a particular month
     * @param month for reference to check day is in range for the month
     * @param year to check for leap year exceptions
     * @return {@code true} date can be found in the calendar
     *         {@code false} otherwise
     */
    private boolean isValidDate(String day, String month, String year){
        if(month.equals("2")){
            if(isLeap(Integer.parseInt(year))){
                if(Integer.parseInt(day) <= 29)
                    return true;
            } else{
                if(Integer.parseInt(day) <= 28)
                    return true;
            }
        } else if(month.equals("4") || month.equals("6") || month.equals("9") || month.equals("11")){
            if(Integer.parseInt(day) <= 30)
                return true;
        } else {
            if(Integer.parseInt(day) <= 31)
                return true;
        }

        return false;
    }

    /**
     *
     * @param year to check whether its a leap year
     * @return {@code true} if it is a leap year
     *         {@code false} otherwise
     */
    private boolean isLeap(int year){
        if(year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)){
            return true;
        }

        return false;
    }

    /**
     *
     * @param time 24-Hour clock
     * @return String of 12-Hour clock with correct suffix
     */
    private String formatTime(String time){
        String hour = time.substring(0,2);
        String min = time.substring(2,4);
        String suffix = (Integer.parseInt(hour) < 12) ? "am" : "pm";

        if(Integer.parseInt(hour) > 23 || Integer.parseInt(min) > 59)
            return null;

        if(Integer.parseInt(hour) == 0)
            hour = "12";
        else if(Integer.parseInt(hour) > 12)
            hour = Integer.toString(Integer.parseInt(hour) - 12);

        if(min.equals("00"))
            return hour + suffix;
        else
            return hour + "." + min + suffix;
    }

    public String getDate(){
        return this.date;
    }

    public String getTime(){
        return this.time;
    }

    public boolean isValidDateTime(){
        if(date == null || time == null)
            return false;

        return true;
    }
}
