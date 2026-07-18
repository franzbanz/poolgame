package game;

import ch.aplu.jgamegrid.Actor;

public abstract class GameObject extends Actor {

//	movable objects constructor
	public GameObject(String sprite) {
		super(sprite);
		addActorCollisionListener(this);
	}

//	immovable objects constructor
//	public GameObject() {
//		addActorCollisionListener(this);
//	}
}
