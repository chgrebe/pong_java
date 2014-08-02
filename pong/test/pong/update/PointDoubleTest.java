package pong.update;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PointDoubleTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddPointDouble() {
		final PointDouble p = new PointDouble(-3.5, 7.8);
		final PointDouble p2 = new PointDouble(2.6, -6.2);

		assertEquals(-0.9, p.add(p2).x, 0.000000000000001);
		assertEquals(1.6, p.add(p2).y, 0.000000000000001);
	}

	@Test
	public void testAddPointDoublePointDouble() {
		PointDouble p1 = new PointDouble(7, 8);
		PointDouble p2 = new PointDouble(8, 8);
		assertEquals(new PointDouble(15, 16).toString(), PointDouble.add(p1, p2).toString());

		p2 = new PointDouble(-8, 8);
		assertEquals(new PointDouble(-1, 16).toString(), PointDouble.add(p1, p2).toString());

		p2 = new PointDouble(-8, -8);
		assertEquals(new PointDouble(-1, 0).toString(), PointDouble.add(p1, p2).toString());

		p2 = new PointDouble(8, -8);
		assertEquals(new PointDouble(15, 0).toString(), PointDouble.add(p1, p2).toString());

		p1 = new PointDouble(-6, -7);
		p2 = new PointDouble(-8, -9);
		assertEquals(new PointDouble(-14, -16).toString(), PointDouble.add(p1, p2).toString());

		p1 = new PointDouble(-3, 7);
		p2 = new PointDouble(2, -6);
		assertEquals(new PointDouble(-1, 1).toString(), PointDouble.add(p1, p2).toString());

		p1 = new PointDouble(-3.5, 7.8);
		p2 = new PointDouble(2.6, -6.2);
		assertEquals(-0.9, PointDouble.add(p1, p2).x, 0.000000000000001);
		assertEquals(1.6, PointDouble.add(p1, p2).y, 0.000000000000001);

		p1 = new PointDouble(7, 8);
		p2 = new PointDouble(-7, -8);
		assertEquals(new PointDouble(0, 0).toString(), PointDouble.add(p1, p2).toString());
	}

	@Test
	public void testParseFrom() {
		final double cos30 = Math.cos(Math.toRadians(30));
		final double sin60 = cos30;

		Vector2D v = new Vector2D(30, 1);
		assertEquals(cos30, PointDouble.parseFrom(v).x, 0.000000000000001);
		assertEquals(0.5, PointDouble.parseFrom(v).y, 0.000000000000001);

		v.length = 1.5;
		assertEquals(cos30 * 1.5, PointDouble.parseFrom(v).x, 0.000000000000001);
		assertEquals(0.75, PointDouble.parseFrom(v).y, 0.000000000000001);

		v = new Vector2D(60, 1);
		assertEquals(0.5, PointDouble.parseFrom(v).x, 0.000000000000001);
		assertEquals(sin60, PointDouble.parseFrom(v).y, 0.000000000000001);

		v = new Vector2D(90, 1);
		assertEquals(0, PointDouble.parseFrom(v).x, 0.000000000000001);
		assertEquals(1, PointDouble.parseFrom(v).y, 0.000000000000001);

		v = new Vector2D(120, 1);
		assertEquals(-0.5, PointDouble.parseFrom(v).x, 0.000000000000001);
		assertEquals(sin60, PointDouble.parseFrom(v).y, 0.000000000000001);

		v = new Vector2D(150, 1);
		assertEquals(-cos30, PointDouble.parseFrom(v).x, 0.000000000000001);
		assertEquals(0.5, PointDouble.parseFrom(v).y, 0.000000000000001);

		v = new Vector2D(180, 1);
		assertEquals(-1, PointDouble.parseFrom(v).x, 0.000000000000001);
		assertEquals(0, PointDouble.parseFrom(v).y, 0.000000000000001);

		v = new Vector2D(210, 1);
		assertEquals(-cos30, PointDouble.parseFrom(v).x, 0.000000000000001);
		assertEquals(-0.5, PointDouble.parseFrom(v).y, 0.000000000000001);

		v = new Vector2D(240, 1);
		assertEquals(-0.5, PointDouble.parseFrom(v).x, 0.000000000000001);
		assertEquals(-sin60, PointDouble.parseFrom(v).y, 0.000000000000001);

		v = new Vector2D(270, 1);
		assertEquals(0, PointDouble.parseFrom(v).x, 0.000000000000001);
		assertEquals(-1, PointDouble.parseFrom(v).y, 0.000000000000001);

		v = new Vector2D(300, 1);
		assertEquals(0.5, PointDouble.parseFrom(v).x, 0.000000000000001);
		assertEquals(-sin60, PointDouble.parseFrom(v).y, 0.000000000000001);

		v = new Vector2D(330, 1);
		assertEquals(cos30, PointDouble.parseFrom(v).x, 0.000000000000001);
		assertEquals(-0.5, PointDouble.parseFrom(v).y, 0.000000000000001);
	}

	@Test
	public void testPointDouble() {
		final PointDouble p = new PointDouble(15, 10);

		assertEquals(15, p.x, 0);
		assertEquals(10, p.y, 0);
	}

}
