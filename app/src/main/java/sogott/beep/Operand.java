package sogott.beep;

import java.lang.constant.Constable;
import java.lang.constant.ConstantDesc;
import java.util.Optional;

final class Operand implements Comparable<Operand>, Constable, CharSequence {

    final private String _string;

    Operand(String string) {
        this._string = string;
    }

    String string() {
        return this._string;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Operand operand && this._string.equals(operand._string);
    }

    @Override
    public int hashCode() {
        return this._string.hashCode();
    }

    @Override
    public int compareTo(Operand other) {
        return this._string.compareTo(other._string);
    }

    @Override
    public Optional<? extends ConstantDesc> describeConstable() {
        return this._string.describeConstable();
    }

    @Override
    public int length() {
        return this._string.length();
    }

    @Override
    public char charAt(int index) {
        return this._string.charAt(index);
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return this._string.subSequence(end, end);
    }
}
