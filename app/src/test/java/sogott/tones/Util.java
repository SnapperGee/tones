package sogott.tones;

import java.util.random.RandomGenerator;
import java.util.stream.Stream;

import static java.util.Arrays.stream;

final class Util {
    private final static RandomGenerator random = RandomGenerator.getDefault();

    static Stream<String> randomStrings(long numOfStrings, long minLength, long maxLength) {
        return Stream.generate(() ->
            random.ints(
                random.nextLong(minLength, maxLength + 1),
                32,
                126
            )
            .collect(
                StringBuilder::new,
                (sb, cp) -> sb.append((char) cp),
                StringBuilder::append)
            .toString()
        )
        .distinct()
        .limit(numOfStrings);
    }

    static Stream<String> randomStrings(long numOfStrings, long minLength, long maxLength, char excludeChar) {
        return Stream.generate(() ->
            random.ints(
                32,
                126
            )
            .filter(cp -> Character.toUpperCase(cp) != Character.toUpperCase(excludeChar))
            .limit(random.nextLong(minLength, maxLength + 1))
            .collect(
                StringBuilder::new,
                (sb, cp) -> sb.append((char) cp),
                StringBuilder::append)
            .toString()
        )
        .distinct()
        .limit(numOfStrings);
    }

    static Stream<String> randomStrings(long numOfStrings, long minLength, long maxLength, int[] excludeCodePoints) {
        return Stream.generate(() ->
            random.ints(
                32,
                126
            )
            .filter(cp -> stream(excludeCodePoints).noneMatch(anotherCp -> Character.toUpperCase(cp) == Character.toUpperCase(anotherCp)))
            .limit(random.nextLong(minLength, maxLength + 1))
            .collect(
                StringBuilder::new,
                (sb, cp) -> sb.append((char) cp),
                StringBuilder::append)
            .toString()
        )
        .distinct()
        .limit(numOfStrings);
    }
}
