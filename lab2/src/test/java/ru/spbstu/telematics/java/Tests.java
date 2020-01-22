package ru.spbstu.telematics.java;

import static org.junit.Assert.*;

import org.junit.Test;

public class Tests {

	@Test
	public void test() {
		MyQueue<Integer> n_val = new MyQueue<Integer>(10);
		for(int i = 0; i<11;i++) {
			n_val.insert(i);
		}
		assertEquals(11,n_val.size());
	}

}
