import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class Analyse {
    private long timer;
    private int arrayLength;
    private int numberOfIterations;
}
