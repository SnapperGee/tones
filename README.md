# Tones

Generate tones musically with the CLI. With this package, you can pass musical
notes via command line arguments and output them as audio and optionally write
them to a WAV file.

[![Java][java shield]][java website]
[![Apache Commons Cli][apache commons cli shield]][apache commons cli website]
[![JUnit5 Jupiter][junit5 jupiter shield]][junit5 jupiter website]
[![Hamcrest][hamcrest shield]][hamcrest website]
[![Mockito][mockito shield]][mockito website]
[![Gradle][gradle shield]][gradle website]
[![Kotlin][kotlin shield]][kotlin website]
![Git][git shield]
[![Github][github shield]][github repo]
[![MIT][mit shield]][mit website]

## Usage

The simplest usage is shown in the example below:

```bash
$tones C4.4 D3.4 E-5.2 E-4.2 D4.2 C3.1
```

This would output a sine wave tone consisting of the following notes to whatever
the current OS's audio output is set to:

- C quarter note in the 4th octave &rightarrow; C&#9833;
- D quarter note in the 3rd octave &rightarrow; D&#9833;
- E flat half note in the 5th octave &rightarrow; E&flat;&#119134;
- E flat half note in the 4th octave &rightarrow; E&flat;&#119134;
- D half note in the 4th octave &rightarrow; D&#119134;
- C whole note in the 3rd octave &rightarrow; C&#119133;

### Command Line Options

- `--bpm`, `b`

  Sets the bpm (beats per minute)/tempo of the audio. Expects a positive integer
  argument and defaults to `140`.

- `--help`, `-h`

  Ignores all other arguments and prints a help message about usage and the
  command line options and flags.

- `--note-beat-value`, `-n`

  Sets the beat value of a note. Expects a positive integer argument and
  defaults to `4`. The simplest way to think of this value is the bottom value
  of a time signature. So if there's a time signature of
  <sup>3</sup>&frasl;<sub>4</sub>, then `4` is the beat value of a note. This
  affects how the duration get's applied to a note and probably doesn't have to
  be manually set in most cases.

- `--out`, `-o`

  Outputs the audio to a 44.1khz/16bit WAV file. Expects a path (or filename)
  that doesn't point to a pre-existing file or directory. `.wav` is appended to
  the outputted file if it doesn't already contain a file extension.

- `--version`, `-v`

  Prints the version of the package.

- `--wave`, `-w`

  Sets the default wave shape to use for notes that don't have a wave shape
  specified. Expects a valid wave shape and defaults to `SINE`. The valid wave
  shapes can be found in the [Wave Shapes section](#wave-shapes) of this
  document.

## Wave Shapes

It's possible to generate tones with 5 different wave shapes. Each wave shape
can be represented via one or more case insensitive `String`s. The following
wave shapes and their strings are:

- Sine &acd;

  The `String`s for this wave shape are: `SINE`.

- Square &#9101;

  The `String`s for this wave shape are: `SQR` and `SQUARE`.

- Triangle &wedge;

  The `String`s for this wave shape are: `TRI` and `TRIANGLE`.

- Saw Up &#9727;

  The `String`s for this wave shape are: `SUP`, `SAWUP` and `SAW_UP`.

- Saw Down &#9722;

  The `String`s for this wave shape are: `SDN`, `SAWDOWN` and `SAW_DOWN`.

## Installation

(coming soon)

[java shield]: https://img.shields.io/badge/java%20JDK%2021-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white "Java JDK 21"
[java website]: https://docs.oracle.com/en/java/javase/21/docs/api/index.html "Java"
[apache commons cli shield]: https://img.shields.io/badge/Apache%20Commons%20CLI-D42029?style=for-the-badge&logo=apache&logoColor=white "Apache Commons CLI"
[apache commons cli website]: https://commons.apache.org/proper/commons-cli/ "Apache Commons CLI"
[junit5 jupiter shield]: https://img.shields.io/badge/JUnit%205%20Jupiter-blue?style=for-the-badge "JUnit5 Jupiter"
[junit5 jupiter website]: https://junit.org/junit5/docs/current/user-guide/ "JUnit5 Jupiter"
[hamcrest shield]: https://img.shields.io/badge/Hamcrest-teal?style=for-the-badge "Hamcrest"
[hamcrest website]: https://hamcrest.org/JavaHamcrest/ "Hamcrest"
[mockito shield]: https://img.shields.io/badge/Mockito-yellow?style=for-the-badge "Mockito"
[mockito website]: https://site.mockito.org/ "Mockito"
[gradle shield]: https://img.shields.io/badge/Gradle-02303A.svg?style=for-the-badge&logo=Gradle&logoColor=white "Gradle"
[gradle website]: https://gradle.org/ "Gradle"
[kotlin shield]: https://img.shields.io/badge/kotlin-%237F52FF.svg?style=for-the-badge&logo=kotlin&logoColor=white "Kotlin"
[kotlin website]: https://kotlinlang.org/ "Kotlin"
[git shield]: https://img.shields.io/badge/git-%23F05033.svg?style=for-the-badge&logo=git&logoColor=white "Git"
[github shield]: https://img.shields.io/badge/github-%23121011.svg?style=for-the-badge&logo=github&logoColor=white "Github"
[github repo]: https://github.com/SnapperGee/tones "Github"
[mit shield]: https://img.shields.io/badge/license-MIT-green?style=for-the-badge "MIT"
[mit website]: https://opensource.org/license/mit "MIT"
