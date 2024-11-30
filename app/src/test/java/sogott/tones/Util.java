package sogott.tones;

import java.util.random.RandomGenerator;
import java.util.stream.Stream;

import static java.util.Arrays.stream;

final class Util {
    private final static RandomGenerator random = RandomGenerator.getDefault();

    static Stream<String> randomStrings(long numOfStrings, int minLength, int maxLength) {
        return Stream.generate(() ->
            random.ints(
                random.nextInt(minLength, maxLength + 1),
                32,
                126
            )
            .mapToObj(cp -> (char) cp)
            .collect(
                StringBuilder::new,
                StringBuilder::append,
                StringBuilder::append)
            .toString()
        )
        .distinct()
        .limit(numOfStrings);
    }

    static Stream<String> randomStrings(long numOfStrings, int minLength, int maxLength, char exclude) {
        return Stream.generate(() ->
            random.ints(
                32,
                126
            )
            .filter(cp -> cp != exclude)
            .limit(random.nextInt(minLength, maxLength + 1))
            .mapToObj(cp -> (char) cp)
            .collect(
                StringBuilder::new,
                StringBuilder::append,
                StringBuilder::append)
            .toString()
        )
        .distinct()
        .limit(numOfStrings);
    }

    static Stream<String> randomStrings(long numOfStrings, int maxLength) {
        return randomStrings(numOfStrings, 0, maxLength);
    }

    static Stream<String> randomStrings(long numOfStrings, int maxLength, char exclude) {
        return randomStrings(numOfStrings, 0, maxLength, exclude);
    }
}
