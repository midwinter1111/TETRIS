package Minos;

import FrameComponent.Field;
import FrameComponent.Mino;

public class OMino extends Mino{
	public OMino(Field field) {
		super(field);

		mino[1][1] = 1;
		mino[1][2] = 1;
		mino[2][1] = 1;
		mino[2][2] = 1;

		imageNo = Mino.OMino;
	}

}
