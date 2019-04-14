package sessiontypes.binary;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import static sessiontypes.binary.TypeLevelNatural.*;

public class BinarySession {
	
	public static <S extends SessionType> Client<S, EndBlock> fork(Consumer<Server<S, EndBlock>> thread) {
		return null;
	}
	
	public static class SessionPair<S extends SessionType> {
		public SessionPair(Server<S, EndBlock> server, Client<S, EndBlock> client) {
			this.server = server;
			this.client = client;
		}
		public final Server<S, EndBlock> server;
		public final Client<S, EndBlock> client;
	}
	
	public static <S extends SessionType, A> SessionPair<S> pipeline(List<A> params, BiConsumer<SessionPair<S>, A> thread) {
		return null;
	}
	
	public static <FS extends IBlock> void close(Server<Close, FS> s) {
		
	}
	
	public static <FS extends IBlock> void close(Client<Close, FS> s) {
		
	}
	
	public static <T, S extends SessionType, FS extends IBlock> Server<S, FS> send(Server<Response<T, S>, FS> s, T v) {
		return null;
	}
	
	public static <T, S extends SessionType, FS extends IBlock> Client<S, FS> send(Client<Request<T, S>, FS> s, T v) {
		return null;
	}
	
	
	public static <T, S extends SessionType, FS extends IBlock> Server<S, FS> receive(Server<Request<T, S>, FS> s, Buf<T> v) {
		return null;
	}
	
	public static <T, S extends SessionType, FS extends IBlock> Client<S, FS> receive(Client<Response<T, S>, FS> s, Buf<T> v) {
		return null;
	}
	
	public static <SL extends SessionType, SR extends SessionType, FS extends IBlock> Server<SL, FS> chooseLeft(Server<ResponseChoice<SL, SR>, FS> s) {
		return null;
	}
	
	public static <SL extends SessionType, SR extends SessionType, FS extends IBlock> Server<SR, FS> chooseRight(Server<ResponseChoice<SL, SR>, FS> s) {
		return null;
	}
	
	
	public static <SL extends SessionType, SR extends SessionType, FS extends IBlock> Client<SL, FS> chooseLeft(Client<RequestChoice<SL, SR>, FS> s) {
		return null;
	}
	
	public static <SL extends SessionType, SR extends SessionType, FS extends IBlock> Client<SR, FS> chooseRight(Client<RequestChoice<SL, SR>, FS> s) {
		return null;
	}
	
	
	public static <SL extends SessionType, SR extends SessionType, FS extends IBlock> void follow(Server<RequestChoice<SL, SR>, FS> s, Consumer<Server<SL, FS>> left, Consumer<Server<SR, FS>> right) {
		
	}
	
	public static <SL extends SessionType, SR extends SessionType, FS extends IBlock> void follow(Client<ResponseChoice<SL, SR>, FS> s, Consumer<Client<SL, FS>> left, Consumer<Client<SR, FS>> right) {
		
	}
	
	public static <S extends SessionType, FS extends IBlock> Server<S, Block<S, FS>> enter(Server<Rec<S>, FS> s) {
		return null;
	}
	
	public static <S extends SessionType, FS extends IBlock> Client<S, Block<S, FS>> enter(Client<Rec<S>, FS> s) {
		return null;
	}
	
	
	public static <S extends SessionType, FS extends IBlock> Server<S, Block<S,FS>> zero(Server<Jump<Zero>, Block<S,FS>> s) {
		return null;
	}
	
	public static <S extends SessionType, FS extends IBlock> Client<S, Block<S,FS>> zero(Client<Jump<Zero>, Block<S,FS>> s) {
		return null;
	}
	
	public static <N extends Num, S extends SessionType, FS extends IBlock> Server<Jump<N>, FS> succ(Server<Jump<Succ<N>>, Block<S,FS>> s) {
		return null;
	}
	
	public static <N extends Num, S extends SessionType, FS extends IBlock> Client<Jump<N>, FS> succ(Client<Jump<Succ<N>>, Block<S,FS>> s) {
		return null;
	}
	
	
	public static class Buf<T> {
		public T content;
	}
	
	
	abstract static class Communicator {
		
	}
	
	public static class Server<S extends SessionType, FS extends IBlock> extends Communicator {
		
	}
	
	public static class Client<S extends SessionType, FS extends IBlock> extends Communicator {
		
	}

	
	interface SessionType { }	
	
	public static final class Request<T, S extends SessionType> implements SessionType { }
	
	public static final class Response<T, S extends SessionType> implements SessionType { }
	
	public static final class RequestChoice<SL extends SessionType, SR extends SessionType> implements SessionType { }
	
	public static final class ResponseChoice<SL extends SessionType, SR extends SessionType> implements SessionType { }

	public static final class Rec<S extends SessionType> implements SessionType { }
	
	public static final class Jump<N extends Num> implements SessionType { }
	
	public static final class Close implements SessionType { }
		
	interface IBlock { }
	
	public static final class EndBlock implements IBlock { }
	
	public static final class Block<S extends SessionType, B extends IBlock> implements IBlock { }
	
}
