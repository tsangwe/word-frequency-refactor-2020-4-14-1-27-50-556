import java.util.*;

public class WordFrequencyGame {

    private static final String SPACE_PATTERN = "\\s+";
    private static final String DEFAULT_DELIMITER = "\n";
    private static final String CALCULATE_ERROR_MESSAGE = "Calculate Error";

    public String getResult(String inputString) {
        try {
            //split the input string with 1 to n pieces of spaces
            String[] words = inputString.split(SPACE_PATTERN);

            List<Input> inputList = new ArrayList<>();
            for (String word : words) {
                Input input = new Input(word, 1);
                inputList.add(input);
            }

            //get the map for the next step of sizing the same word
            Map<String, List<Input>> map = convertToMap(inputList);

            List<Input> list = new ArrayList<>();
            for (Map.Entry<String, List<Input>> entry : map.entrySet()) {
                Input input = new Input(entry.getKey(), entry.getValue().size());
                list.add(input);
            }
            inputList = list;

            inputList.sort((firstInput, secondInput) -> secondInput.getWordCount() - firstInput.getWordCount());

            StringJoiner joiner = new StringJoiner(DEFAULT_DELIMITER);
            for (Input input : inputList) {
                String formattedString = input.getWord() + " " + input.getWordCount();
                joiner.add(formattedString);
            }
            return joiner.toString();
        } catch (Exception exception) {
            return CALCULATE_ERROR_MESSAGE;
        }
    }

    private Map<String, List<Input>> convertToMap(List<Input> inputList) {
        Map<String, List<Input>> map = new HashMap<>();
        for (Input input : inputList) {
            if (!map.containsKey(input.getWord())) {
                ArrayList arr = new ArrayList<>();
                arr.add(input);
                map.put(input.getWord(), arr);
            } else {
                map.get(input.getWord()).add(input);
            }
        }
        return map;
    }
}
