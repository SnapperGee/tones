/**
 * This package contains classes that enable the synthesis of tones in a
 * musical format via the command line interface. The tones can be audibly
 * played and/or written to a WAV file. It achieves this by interpreting and
 * converting {@code String}s and {@code char}s, formatted in a structured
 * music-like notation, into the properties needed to synthesize audio. These
 * properties can then be used to generate {@code byte} buffers appropriate for
 * synthesizing PCM audio.
 *
 * <p>
 * There are 3 properties needed to synthesize audio that are defined in the
 * {@link sogott.tones.Audio Audio} class. These properties are:
 *
 * <ol>
 * <li><strong><em>Wave Shape</em></strong>
 * <li><strong><em>Frequency</em></strong> (pitch)
 * <li><strong><em>Duration</em></strong>
 * </ol>
 *
 * <h2>Audio String</h2>
 * An <strong><em>audio string</em></strong> is a {@code String} that can be
 * used to synthesize audio. The {@code String} can be divided up into 3
 * segments, each segment corresponding to the above properties. The
 * {@link sogott.tones.AudioString AudioString} class contains static methods
 * for parsing {@code String}s to {@link sogott.tones.Audio Audio} objects.
 * Below is an example an audio string:
 *
 * <pre>{@code SIN>C+4.4}</pre>
 *
 * Here is a breakdown of the above {@code String}'s segments and how they
 * correspond to the audio properties:
 *
 * <h3>Wave Shape (prefix)</h3>
 * The leading {@code "SIN"} segment indicates the audio wave shape is a
 * <i>sine</i> wave. The right angle bracket character {@code '>'} delimits
 * this segment from the following segment.
 *
 * <h3>Pitch/Frequency</h3>
 * The segment after the wave shape prefix, {@code "C=4"}, indicates the
 * <i>pitch</i> (or <i>frequency</i>) of the audio. The period character
 * {@code '.'} delimits this segment from the final segment.
 *
 * <h3>Duration (suffix)</h3>
 * The suffix integer {@code 4} indicates that the audio is a quarter note
 * (explained further down).
 *
 * <h2>Wave Shape</h2>
 * Wave shapes are mathematically derived curves. The static methods of the
 * {@link sogott.tones.GenerateWaveByteBuffer GenerateWaveByteBuffer} generate
 * the different wave shapes (as byte buffers appropriate for synthesizing PCM
 * audio). There are 5 shapes this package is capably of synthesizing defined in
 * the {@link sogott.tones.Wave Wave} enum. The shapes are:
 *
 * <ol>
 * <li><strong><em>SINE</em></strong> &acd;
 * <li><strong><em>SQUARE</em></strong> &#9101;
 * <li><strong><em>TRIANGLE</em></strong> &wedge;
 * <li><strong><em>SAW UP</em></strong> &#9727;
 * <li><strong><em>SAW DOWN</em></strong> &#9722;
 * </ol>
 *
 * If audio is silence, it does not have a wave shape (nor pitch) and only has
 * a duration.
 *
 * <h2>Pitch/Frequency</h2>
 * The <strong><em>pitch</em></strong> indicates the
 * <strong><em>frequency</em></strong> and is composed of 3 properties that are
 * defined in the {@link sogott.tones.Pitch Pitch} class. These properties are:
 *
 * <ol>
 * <li><strong><em>Pitch Letter</em></strong>
 * <li><strong><em>Accidental</em></strong>
 * <li><strong><em>Octave</em></strong>
 * </ol>
 *
 * If audio is silence, it does not have a pitch (nor wave shape) and only has
 * a duration.
 *
 * <h3>Pitch Letter</h3>
 * The <strong><em>pitch letter</em></strong> is the letter of a musical note and
 * corresponds to one of the characters A-G.
 *
 * <h3>Accidental</h3>
 * The <strong><em>accidental</em></strong> indicates wether the musical note
 * is a <i>sharp</i> &sharp; or <i>flat</i> &flat;. Or if it's a <i>natural</i>
 * &natural; (neither a <i>sharp</i> &sharp; nor a <i>flat</i> &flat;), then
 * this character can be omitted. The 3 characters used to represent the 3 types
 * of accidentals are:
 *
 * <ol>
 * <li>A minus sign, {@code '-'}, character for <i>flat</i> &flat;
 * <li>An equals sign, {@code '='}, character for <i>natural</i> &natural;
 * (character is optional and can be omitted)
 * <li>A plus sign, {@code '+'}, character for <i>sharp</i> &sharp;
 * </ol>
 *
 * <h3>Octave</h3>
 * The final segment of a pitch is the octave, which is simply a non negative
 * {@code int} (0 or greater) to indicate which octave the note is in.
 *
 * <h2>Duration</h2>
 * The final segment of an audio string is its duration which is simply a
 * positive integer (greater than 0). The duration is <strong><b>relative to the
 * tempo/bpm and note beat value</b></strong>. This duration integer alone isn't
 * enough to extrapolate the actual span of time the audio will play for. The
 * tempo/bpm of the playback and the value each beat has must also be known to
 * be able to use this value to create the span of time the audio will play.
 *
 * <p>
 * The simplest way to think of it is that if the duration is integer <i>N</i>,
 * then the length of the audio will be <sup>1</sup>&frasl;<sub><i>N</i></sub>.
 * So, if <i>N</i> were 1, then the duration would be
 * <sup>1</sup>&frasl;<sub>1</sub> which would be a whole note. If <i>N</i> were
 * 4, that'd result in <sup>1</sup>&frasl;<sub>4</sub> so it'd be a quarter note
 * etc.
 */

package sogott.tones;
