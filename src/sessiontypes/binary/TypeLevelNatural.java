package sessiontypes.binary;

public class TypeLevelNatural {
	abstract interface Num {}
	public static final class Zero implements Num { } 
	public static final class Succ<N extends Num> implements Num { } 
}
