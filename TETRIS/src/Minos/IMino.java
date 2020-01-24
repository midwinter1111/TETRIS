package Minos;

import FrameComponent.Field;
import FrameComponent.Mino;

public class IMino extends Mino{

	public IMino(Field field) {
		super(field);

		mino[0][1] = 1;
		mino[1][1] = 1;
		mino[2][1] = 1;
		mino[3][1] = 1;

		imageNo = Mino.IMino;
	}
}
