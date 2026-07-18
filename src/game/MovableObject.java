package game;

import ch.aplu.jgamegrid.Actor;
import ch.aplu.jgamegrid.Location;

public abstract class MovableObject extends GameObject {

	double drag;
	double speedX;
	double speedY;
	double tmpX;
	double tmpY;
	double maxSpeed;
	double velocity;
	int size;
	boolean positionInitialized;

	public MovableObject(String sprite, int size) {
		super(sprite);
		this.size = size;

		speedX = 0.0;
		speedY = 0.0;
		tmpX = 0.0;
		tmpY = 0.0;
		drag = InitialConditions.getDrag();
		maxSpeed = InitialConditions.getMaxSpeed();
		positionInitialized = false;
	}

	@Override
	public void move() {

//		use double data type for more precision
		if (!positionInitialized) {
			tmpX = getX();
			tmpY = getY();
			positionInitialized = true;
		}

//		apply upper speedlimit
//		speedX = Math.max(-maxSpeed, Math.min(speedX, maxSpeed));
//		speedY = Math.max(-maxSpeed, Math.min(speedY, maxSpeed));

//		apply drag
		velocity = Math.sqrt(speedX * speedX + speedY * speedY);

		if (velocity > 0) {
			double newVelocity = Math.max(0, velocity - velocity * drag);
			speedX = speedX / velocity * newVelocity;
			speedY = speedY / velocity * newVelocity;
			if (velocity < 0.1) {
				speedX = 0.0;
				speedY = 0.0;
			}
		}
//		update position
		tmpX += speedX;
		tmpY += speedY;
		setLocation(new Location((int) Math.round(tmpX), (int) Math.round(tmpY)));
		Wall.handleBoundary(this);
	}

	@Override
	public int collide(Actor actor1, Actor actor2) {

		if (actor2 instanceof MovableObject) {
			handleCollision((MovableObject) actor1, (MovableObject) actor2);
		} else if (actor2 instanceof Hole) {
			handleHole((MovableObject) actor1);
		}
		return 0;

	}

	public void handleCollision(MovableObject a, MovableObject b) {
		double dx = b.tmpX - a.tmpX;
		double dy = b.tmpY - a.tmpY;
		double d = Math.sqrt(dx * dx + dy * dy);

		double nx = dx / d;
		double ny = dy / d;
		double tx = -ny;
		double ty = nx;

		double v1n = a.speedX * nx + a.speedY * ny;
		double v1t = a.speedX * tx + a.speedY * ty;
		double v2n = b.speedX * nx + b.speedY * ny;
		double v2t = b.speedX * tx + b.speedY * ty;

		double swap = v1n;
		v1n = v2n;
		v2n = swap;

		a.speedX = nx * v1n + tx * v1t;
		a.speedY = ny * v1n + ty * v1t;
		b.speedX = nx * v2n + tx * v2t;
		b.speedY = ny * v2n + ty * v2t;

		double overlap = (a.size / 2.0 + b.size / 2.0) - d;
		if (overlap > 0) {
			double pushX = nx * overlap / 2;
			double pushY = ny * overlap / 2;
			a.tmpX -= pushX;
			a.tmpY -= pushY;
			b.tmpX += pushX;
			b.tmpY += pushY;
			a.setLocation(new Location((int) Math.round(a.tmpX), (int) Math.round(a.tmpY)));
			b.setLocation(new Location((int) Math.round(b.tmpX), (int) Math.round(b.tmpY)));
		}
	}

	public void handleHole(MovableObject a) {
		if (a instanceof Cueball) {
			a.setSpeedX(0);
			a.setSpeedY(0);
			a.setTmpX(InitialConditions.getCenter().x);
			a.setTmpY(InitialConditions.getCenter().y);
		} else if (a instanceof Ball) {
			a.removeSelf();
		}
	}

	public double getSpeedX() {
		return speedX;
	}

	public void setSpeedX(double speedX) {
		this.speedX = speedX;
	}

	public double getSpeedY() {
		return speedY;
	}

	public void setSpeedY(double speedY) {
		this.speedY = speedY;
	}

	public int getSize() {
		return size;
	}

	public double getTmpX() {
		return tmpX;
	}

	public void setTmpX(double tmpX) {
		this.tmpX = tmpX;
	}

	public double getTmpY() {
		return tmpY;
	}

	public void setTmpY(double tmpY) {
		this.tmpY = tmpY;
	}

	public double getVelocity() {
		return velocity;
	}

}
