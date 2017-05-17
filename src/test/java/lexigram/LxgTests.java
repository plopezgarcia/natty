package lexigram;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import com.joestelmach.natty.*;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class LxgTests {

    @Test
    public void dateExtractionFileTest() throws Exception {

        Parser parser = new Parser();
        List groups = parser.parse(FileUtils.readFileToString(new File("src/test/resources/dates/i2b2.txt")));
        for (Object group : groups) {
            //System.out.println("DATEGROUP");
            DateGroup dg = (DateGroup) group;
            //System.out.println("Full text: " + dg.getFullText());
            //System.out.println("Parse locations: " + dg.getParseLocations());
            //System.out.println("Is date inferred: " + dg.isDateInferred());

            if (isExplicitDate(dg)) {
                Date date = dg.getDates().get(0);
                System.out.println(dg.getLine() + ": " + new SimpleDateFormat("MM-dd-yyyy").format(date));
            }
        }
    }

    private boolean isExplicitDate(DateGroup dg) {
        return !dg.isDateInferred() &&
                dg.getSyntaxTree().toStringTree().contains("EXPLICIT_DATE") &&
                dg.getSyntaxTree().toStringTree().contains("MONTH_OF_YEAR") &&
                dg.getSyntaxTree().toStringTree().contains("DAY_OF_MONTH") &&
                dg.getSyntaxTree().toStringTree().contains("YEAR_OF");
    }
}