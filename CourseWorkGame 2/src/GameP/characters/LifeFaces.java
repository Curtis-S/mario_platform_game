/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameP.characters;

import city.cs.engine.Body;
import city.cs.engine.BodyImage;
import city.cs.engine.BoxShape;
import city.cs.engine.GhostlyFixture;
import city.cs.engine.Shape;

// this is the class for the life icons which are displayed on top of the level 
/**
 * Extends Ghostly fixture and  the faces for lives.
 */
public class LifeFaces extends GhostlyFixture {

    private static final Shape faceLife = new BoxShape(0.5f, 0.5f);

    private static final BodyImage lifeFaceImage
            = new BodyImage("data/mahead.png", 1.25f);

    // constructor 
    /**
     * Creates a Ghostly fixture and then adds an image to the body. .
     *
     * @param b is the body of the fixture.
     */
    public LifeFaces(Body b) {
        super(b, faceLife);
        b.addImage(lifeFaceImage);

    }

}
