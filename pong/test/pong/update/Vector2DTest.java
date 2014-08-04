package pong.update;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Vector2DTest {
	private final double sqrt2 = Math.sqrt(2);

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddVector2D() {
		final Vector2D v1 = new Vector2D(45, 1);
		final Vector2D v2 = new Vector2D(225, 1);
		final Vector2D v3 = v1.add(v2);
		final Vector2D v0 = new Vector2D(0, 0);

		assertEquals(v0.length, v3.length, 0.000001);
		assertEquals(v0.angle, v3.angle, 0.000001);
	}

	@Test
	public void testAddVector2DVector2D() {
		Vector2D v1 = new Vector2D(0, 1);
		Vector2D v2 = new Vector2D(90, 1);
		Vector2D v3 = Vector2D.add(v1, v2);

		assertEquals(45.0, v3.angle, 0.000001);
		assertEquals(sqrt2, v3.length, 0.000001);

		v1 = new Vector2D(180, 1);
		v2 = new Vector2D(90, 1);
		v3 = Vector2D.add(v1, v2);

		assertEquals(135.0, v3.angle, 0.000001);
		assertEquals(sqrt2, v3.length, 0.000001);

		v1 = new Vector2D(45, 1);
		v2 = new Vector2D(225, 1);
		v3 = Vector2D.add(v1, v2);
		// PointDouble p1 = PointDouble.parseFrom(v1);
		// PointDouble p2 = PointDouble.parseFrom(v2);
		// PointDouble p3 = PointDouble.parseFrom(v3);
		// System.out.println(p1);
		// System.out.println(p2);
		// System.out.println(p3);
		// System.out.println(PointDouble.add(p1, p2));
		//
		//
		// System.out.println(v3);
		// System.out.println(PointDouble.parseFrom(v3));
		assertEquals(0, v3.length, 0.000001);
		assertEquals(0.0, v3.angle, 0.000001);

	}

	@Test
	public void testParseFrom() {
		PointDouble p = new PointDouble(0, 0);
		assertEquals(0.0, Vector2D.parseFrom(p).length, 0);
		assertEquals(0.0, Vector2D.parseFrom(p).angle, 0);

		p = new PointDouble(1, 0);
		assertEquals(1.0, Vector2D.parseFrom(p).length, 0);
		assertEquals(0.0, Vector2D.parseFrom(p).angle, 0);

		p = new PointDouble(1, 1);
		assertEquals(sqrt2, Vector2D.parseFrom(p).length, 0);
		assertEquals(45.0, Vector2D.parseFrom(p).angle, 0.000001);

		p = new PointDouble(0, 1);
		assertEquals(1.0, Vector2D.parseFrom(p).length, 0);
		assertEquals(90.0, Vector2D.parseFrom(p).angle, 0);

		p = new PointDouble(-1, 1);
		assertEquals(sqrt2, Vector2D.parseFrom(p).length, 0);
		assertEquals(135.0, Vector2D.parseFrom(p).angle, 0.000001);

		p = new PointDouble(-1, 0);
		assertEquals(1.0, Vector2D.parseFrom(p).length, 0);
		assertEquals(180.0, Vector2D.parseFrom(p).angle, 0);

		p = new PointDouble(-1, -1);
		assertEquals(sqrt2, Vector2D.parseFrom(p).length, 0);
		assertEquals(225.0, Vector2D.parseFrom(p).angle, 0);

		p = new PointDouble(0, -1);
		assertEquals(1.0, Vector2D.parseFrom(p).length, 0);
		assertEquals(270.0, Vector2D.parseFrom(p).angle, 0);

		p = new PointDouble(1, -1);
		assertEquals(sqrt2, Vector2D.parseFrom(p).length, 0);
		assertEquals(315.0, Vector2D.parseFrom(p).angle, 0);

		p = new PointDouble(1, -2);
		assertEquals(2.23606797749979, Vector2D.parseFrom(p).length, 0.000001);
		assertEquals(296.565051177078, Vector2D.parseFrom(p).angle, 0.000001);

	}

	@Test
	public void testToString() {
		final Vector2D v = new Vector2D(45.67, 1.2345);
		assertEquals("Length: 1.2345, Angle: 45.67°.", v.toString());
	}

	@Test
	public void testVector2D() {
		final Vector2D v = new Vector2D(45.6, 1.5);
		assertEquals(45.6, v.angle, 0);
	}

}
