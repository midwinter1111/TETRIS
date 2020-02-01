package FrameComponent;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import mockit.Deencapsulation;
import mockit.integration.junit4.JMockit;

/**
 * Test class for "Field" class
 *
 * @author winter
 *
 */
@RunWith(JMockit.class)
class FieldTest {
	int[][] mino = {
			{ 0, 1, 0, 0 },
			{ 0, 1, 0, 0 },
			{ 0, 1, 0, 0 },
			{ 0, 1, 0, 0 }
	};
	Field field = new Field();

	@Test
	void testIsMovable_enableMove() {
		// setup
		Point point = new Point(3, 3);
		// run
		boolean result = field.isMovable(point, mino);
		// verification
		assertThat(result, is(true));
	}

	@Test
	void testIsMovable_invalidXPos() {
		// setup
		Point point = new Point(-5, 3);
		// run
		boolean result = field.isMovable(point, mino);
		// verification
		assertThat(result, is(false));
	}

	@Test
	void testIsMovable_diaableMoveUnderXPos() {
		// setup
		Point point = new Point(-1, 3);
		// run
		boolean result = field.isMovable(point, mino);
		// verification
		assertThat(result, is(false));
	}

	@Test
	void testIsMovable_diaableMoveOverXPos() {
		// setup
		Point point = new Point(10, 3);
		// run
		boolean result = field.isMovable(point, mino);
		// verification
		assertThat(result, is(false));
	}

	@Test
	void testDeleteLine_deleteZeroLine() {
		// setup
		int[][] newField = new int[Field.ROW][Field.COL];
		Deencapsulation.setField(field, "field", newField);
		int expected = 0;
		// run
		int result = field.deleteLine();

		// verification
		assertEquals(result, expected);
	}

	@Test
	void testDeleteLine_deleteSomeOfLine() {
		// setup
		int deleteLines = 2;
		int[][] newField = new int[Field.ROW][Field.COL];
		for (int i = 0; i < deleteLines; i++) {
			for (int j = 0; j < Field.COL; j++) {
				newField[i][j] = 1;
			}
		}
		Deencapsulation.setField(field, "field", newField);
		int expected = deleteLines;
		// run
		int result = field.deleteLine();

		// verification
		assertEquals(result, expected);
	}

	@Test
	void testIsStacked() {
		// setup
		int[][] newField = new int[Field.ROW][Field.COL];
		newField[0][1] = 1;
		Deencapsulation.setField(field, "field", newField);
		// verification
		assertThat(field.isStacked(), is(true));
	}

	@Test
	void testIsStacked_notStacked() {
		// setup
		int[][] newField = new int[Field.ROW][Field.COL];
		Deencapsulation.setField(field, "field", newField);
		// verification
		assertThat(field.isStacked(), is(false));
	}

}
