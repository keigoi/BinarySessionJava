package sessiontypes.example;

import java.util.Random;

import sessiontypes.binary.TypeLevelNatural.Zero;

import static sessiontypes.binary.BinarySession.*;

public class Ordinals {
	public static void main(String[] args) throws InterruptedException {
		int m = 20;
		var r = new Random();
		for(int i = 0; i < m; i++) {
			System.out.println(i + " 回目");
			example1(r.nextInt(200));
			System.out.println();
		}
	}
	
	public static void example1(int n) throws InterruptedException {
		
		var client = fork((Server<Request<Integer, Response<String, Close>>, EndBlock> server) -> {
			System.out.println("Server start");
			var buf = new Buf<Integer>();
			var server1 = receive(server, buf);
			System.out.println("Server received: " + buf.content);
			var ord = toOrdinal(buf.content, false);
			System.out.println("Server will send: " + ord);
			var server2 = send(server1, ord);
			System.out.println("Server end");
			close(server2);
		});
		
		System.out.println("Client start");
		Thread.sleep(1000);
		System.out.println("Client will send: " + n);
		var client1 = send(client, n);
		var buf = new Buf<String>();
		var client2 = receive(client1, buf);
		System.out.println("Client received: " + buf.content);
		close(client2);
		System.out.println("Client End");
	}
	
	public static void example2(int dividend, int divisor) {
		var client = fork((Server<Request<Integer, Request<Integer, ResponseChoice<Response<Integer, Close>, Response<String, Close>>>>, EndBlock> server) -> {
			var buf1 = new Buf<Integer>();
			var buf2 = new Buf<Integer>();
			var server1  = receive(receive(server, buf1), buf2);
			if (buf2.content == 0) {
				close(send(chooseRight(server1), "Divide by zero"));
			} else {
				close(send(chooseLeft(server1), buf1.content / buf2.content));
			}
		});
		
		var c2 = send(send(client, dividend), divisor);
		follow(c2, c -> {
			close(receive(c, new Buf<>())); // TODO
		}, c -> {
			close(receive(c, new Buf<>())); // TODO
		});
	}
	
	public static void example3(int n) {
		var client = fork((Server<Rec<Request<Integer, Response<String, RequestChoice<Jump<Zero>, Close>>>>, EndBlock> serv) -> {
			var s1 = enter(serv);
			while(true) {
				var buf = new Buf<Integer>();
				var s2 = send(receive(s1, buf), "TODO");
				follow(s2, s3 -> {
					
					// Error: Local variable s1 defined in an enclosing scope must be final or effectively final
					// s1 = zero(s3);
					// usual technique is to turn s1 into an array but I do not write s1's type
					
				}, s3 -> {
					close(s3);
				});
			}
		});
		
		var buf = new Buf<String>();
		close(chooseRight(receive(send(zero(chooseLeft(receive(send(enter(client), 100), buf))), 200), buf)));
	}
	
	private static int mod(int dividend, int divisor) {
		return (dividend % divisor + Math.abs(divisor)) % divisor;
	}
	
	private static int digit(int i, int n) {
		if (n < 0)
		{
			throw new IllegalArgumentException("Digit should be a natural number.");
		}
		i = Math.abs(i);
		while (n != 0)
		{
			i /= 10;
			n--;
		}
		return mod(i, 10);
	}
	
	private static String toOrdinal(Integer n, boolean onlySuffix) {
		if (digit(n, 1) == 1)
		{
			return n.toString() + "th";
		}
		else
		{
			switch (digit(n, 0))
			{
				case 1:
					return (onlySuffix ? "" : n.toString()) + "st";
				case 2:
					return (onlySuffix ? "" : n.toString()) + "nd";
				case 3:
					return (onlySuffix ? "" : n.toString()) + "rd";
				default:
					return (onlySuffix ? "" : n.toString()) + "th";
			}
		}
}	
}
