/**
 * Read web server data and analyse hourly access patterns.
 * 
 * @author Cristian Solis
 * @version    2026.03.29
 */
public class LogAnalyzer
{
    // Where to calculate the hourly access counts.
    private int[] hourCounts;
    // Use a LogfileReader to access the data.
    private LogfileReader reader;

    /**
     * Create an object to analyze hourly web accesses.
     * 
     */
    public LogAnalyzer(String filename)
    { 
        // Create the array object to hold the hourly
        // access counts.
        hourCounts = new int[24];
        // Create the reader to obtain the data.
        reader = new LogfileReader(filename);
    }

    /**
     * Analyze the hourly access data from the log file.
     */
    public void analyzeHourlyData()
    {
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int hour = entry.getHour();
            hourCounts[hour]++;
        }
    }

    /**
     * Print the hourly counts.
     * These should have been set with a prior
     * call to analyzeHourlyData.
     */
    public void printHourlyCounts()
    {
        System.out.println("Hr: Count");
        for(int hour = 0; hour < hourCounts.length; hour++) {
            System.out.println(hour + ": " + hourCounts[hour]);
        }
    }
    
    /**
     * Print the lines of data read by the LogfileReader
     */
    public void printData()
    {
        reader.printData();
    }
    
    /**
     * Uses a for loop that uses hourCounts array to get the data that 
     * is stored into total and returns it.
     */
    public int numberOfAccesses()
    {
        int total = 0;
        for(int i = 0; i < hourCounts.length; i++)
        {
            total += hourCounts[i];
        }
        return total;
    }
    
    /**
     * Similar to numberOfAccesses method but checks if amount is less then 
     * a data point in the hourCounts array. If so sets the hours in that hour to amount
     * and sets hour to i and in the end returns hour.
     */
    public int busiestHour()
    {
        int amount = 0;
        int hour = 0;
        
        for(int i = 0; i < hourCounts.length; i++)
        {
            if(amount < hourCounts[i])
            {
                amount = hourCounts[i];
                hour = i;
            }
        }
        return hour;
    }
    
    /**
     * Same thing as busiestHour() method but looking for the smallest and uses a Integer.MAX_VALUE
     * to make sure it doesn't do something wrong and returns i.
     */
    public int quietestHour()
    {
        int amount = Integer.MAX_VALUE;
        int hour = 0;
        
        for(int i = 0; i < hourCounts.length; i++)
        {
            if(amount > hourCounts[i])
            {
                amount = hourCounts[i];
                hour = i;
            }
        }
        return hour;
    }
    
    /**
     * Looks through hourCounts.length-1 to prevent off by 1 as checks i and i+1 and if
     * more then amount sets amount to hours in both and hour to i and returns i.
     */
    public int busiestTwoHour()
    {
        int amount = 0;
        int hour = 0;
        
        for(int i = 0; i < hourCounts.length-1; i++)
        {
            if(amount < hourCounts[i] + hourCounts[i+1])
            {
                amount = hourCounts[i] + hourCounts[i+1];
                hour = i;
            }
        }
        return hour;
    }
}
