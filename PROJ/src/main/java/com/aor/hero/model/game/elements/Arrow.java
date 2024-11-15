package com.aor.hero.model.game.elements;

import com.aor.hero.model.Position;

public class Arrow extends Element{
    public Arrow(int x, int y) {
        super(x,y);
    }

    public Arrow(Position arrowPosition) {
        super(arrowPosition);
    }
}
