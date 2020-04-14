import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WordFrequencyGame {

    private static final String SPACE_PATTERN = "\\s+";
    private static final String DEFAULT_DELIMITER = "\n";

    public String getResult(String inputString) {
        Map<String, Long> frequencyMap = Stream.of(inputString.split(SPACE_PATTERN))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        List<Input> inputs = new ArrayList<>();
        frequencyMap.forEach((word, count) -> inputs.add(new Input(word, Math.toIntExact(count))));
        inputs.sort((firstInput, secondInput) -> secondInput.getWordCount() - firstInput.getWordCount());
        StringJoiner joiner = new StringJoiner(DEFAULT_DELIMITER);
        inputs.forEach(input -> {
            String formattedString = String.format("%s %s", input.getWord(), input.getWordCount());
            joiner.add(formattedString);
        });

        return joiner.toString();
    }
}
