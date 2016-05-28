package com.samurai.el.field;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.samurai.el.maingame.GameInstance;
import com.samurai.el.player.Player;
import com.samurai.el.resource.Resources;

public class Block extends Sprite{
	public boolean isVisible;
	public Player owner;
	public int id;
	public int side;
	public int playerIdOn;
	public int viewerNum;
	public boolean isHome;
	public boolean isPlanet;
	public boolean isConquerPoint;
	public Player playerOn;
	public Texture block6; // invisible block
	public Texture block7; // neutral block
	public Texture planetTexture;
	public ParticleEffect[] homeEffect;
	public ParticleEffect currentEffect;
	public int size;
	public Vector2 planetPosition;// only useful when it's a planet
	public float conquerPointRefreshTime;
	public Sprite plus5;
	public float plusAlpha;
	public float epackSpawnDelay;
	public boolean containAEpack;
	public Sprite epackContainHint;
	
	public float[] alpha;
	public int alphaPointer;
	public int alphaDelay;
	
	public Block(int size) {
		super();
		this.size = size;
		
		viewerNum = 0;
		playerIdOn = -1;
		id = -1;
		owner = null;
		side = -1;
		isVisible = false;
		block6 = Resources.getInstance().block6;
		block7 = Resources.getInstance().block7;
		this.set(new Sprite(block6));
		isHome = false;
		isPlanet = false;
		isConquerPoint = false;
		
		alpha = new float[]{
		0.4f, 0.45f, 0.5f, 0.55f, 0.6f, 0.65f, 0.7f, 0.75f, 0.8f, 0.85f, 0.9f,
		0.85f, 0.8f, 0.75f, 0.7f, 0.65f, 0.6f, 0.55f, 0.5f, 0.45f
		};
		alphaPointer = 0;
		alphaDelay = 0;
		conquerPointRefreshTime = 1f;
		epackSpawnDelay = 5f;
		containAEpack = false;
		epackContainHint = new Sprite();
		epackContainHint.set(Resources.getInstance().epackContainHint);
	}
	
	/**set it to be a planet, with texture id */
	public void setPlanet(int planetId, Vector2 position) {
		switch(planetId) {
		case 0:
			planetTexture = Resources.getInstance().planet0;
			break;
		case 1:
			planetTexture = Resources.getInstance().planet1;
			break;
		case 2:
			planetTexture = Resources.getInstance().planet2;
			break;
		case 3:
			planetTexture = Resources.getInstance().planet3;
			break;
		case 4:
			planetTexture = Resources.getInstance().planet4;
			break;
		}
		this.planetPosition = position;
		if(isVisible) {
			this.setTexture(planetTexture);
		}
		isPlanet = true;
		epackContainHint.setPosition(this.getX()-4, this.getY()-4);
	}
	
	public void setConquerPoint() {
		isConquerPoint = true;
		plus5 = new Sprite();
		plusAlpha = 1;
	}
	
	public void effectInitialize(ParticleEffect[] homeEffect) {
		this.homeEffect = homeEffect;
		this.homeEffect[0].setPosition(this.getX()+this.size/2, this.getY()+this.size/2);
		this.homeEffect[1].setPosition(this.getX()+this.size/2, this.getY()+this.size/2);
		currentEffect = homeEffect[0];
	}
	
	public void playerArrive(Player player) {
		playerOn = player;
		if(GameInstance.getInstance().mode != 2) {
			playerIdOn = playerOn.id;
		} else {
			playerIdOn = playerOn.conquerId;
		}
		if(isVisible) {
			playerOn.isVisible = true;
		}
		if(isHome && playerOn.isRecovering) {
			currentEffect = homeEffect[1];
		}
	}
	
	public void playerArrive(int id) {
		GameInstance tempInstance = GameInstance.getInstance();
		switch(id) {
		case 0:
			playerArrive(tempInstance.redSpear);
			break;
		case 1:
			playerArrive(tempInstance.redSword);
			break;
		case 2:
			playerArrive(tempInstance.redAxe);
			break;
		case 3:
			playerArrive(tempInstance.blueSpear);
			break;
		case 4:
			playerArrive(tempInstance.blueSword);
			break;
		case 5:
			playerArrive(tempInstance.blueAxe);
			break;
		}
	}
	
	public void playerLeave() {
		if(playerOn != null) {
			playerOn.isVisible = false;
		}
		playerOn = null;		
		playerIdOn = -1;		
	}
	
	public void recoverComplete() {
		currentEffect = homeEffect[0];
	}
	
	public void occupy(int id) {
		this.id = id;
		GameInstance tempInstance = GameInstance.getInstance();
		if(GameInstance.getInstance().mode != 2){
			switch(id) {
			case 0:
				owner = tempInstance.redSpear;
				side = 0;
				planetTexture = Resources.getInstance().planet0;
				break;
			case 1:
				owner = tempInstance.redSword;
				side = 0;
				planetTexture = Resources.getInstance().planet0;
				break;
			case 2:
				owner = tempInstance.redAxe;
				side = 0;
				planetTexture = Resources.getInstance().planet0;
				break;
			case 3:
				owner = tempInstance.blueSpear;
				side = 1;
				planetTexture = Resources.getInstance().planet1;
				break;
			case 4:
				owner = tempInstance.blueSword;
				side = 1;
				planetTexture = Resources.getInstance().planet1;
				break;
			case 5:
				owner = tempInstance.blueAxe;
				side = 1;
				planetTexture = Resources.getInstance().planet1;
				break;
			case -1:
				owner = null;
				side = -1;
				planetTexture = Resources.getInstance().planet2;
				break;
			}
		} else {
			if(id == -1) {
				owner = null;
				side = -1;
				planetTexture = Resources.getInstance().planet2;
			} else {
				if(id <= 7) {
					owner = tempInstance.redPlayers.get(id);
					side = 0;
					if(isConquerPoint) {
						plus5.set(Resources.getInstance().plus5_0);
						plus5.setPosition(this.getX()+2, this.getY()+this.getHeight()/4);
					}
					planetTexture = Resources.getInstance().planet0;
				} else {
					owner = tempInstance.bluePlayers.get(id-8);
					side = 1;
					if(isConquerPoint) {
						plus5.set(Resources.getInstance().plus5_1);
						plus5.setPosition(this.getX()+2, this.getY()+this.getHeight()/4);
					}
					planetTexture = Resources.getInstance().planet1;
				}
			}
		}
		
		
		//implements to set texture
		if(isVisible && !isPlanet && owner != null && !isHome) {
			this.setTexture(owner.specBlockTexture);
		}
		else if(isVisible && isPlanet) {
			this.setTexture(planetTexture);
		}
		if(owner == null) {
			this.setTexture(block7);
		}
		
	}
	
	public void occupy(Player p) {
		if(GameInstance.getInstance().mode != 2) {
			occupy(p.id);
		} else {
			occupy(p.conquerId);			
		}
	}
	
	
	public void hide() {
		viewerNum -= 1;
		if(viewerNum == 0) {
			isVisible = false;
			if((playerOn != null) && (!playerOn.isAllied)) {
				playerOn.isVisible = false;
			}
			this.setTexture(block6);
		}
	}
	
	public void show() {
		
		if(viewerNum == 0) {
			isVisible = true;
			if(playerOn != null) {
				playerOn.isVisible = true;
			}
			if(!isPlanet) {
				if(owner != null) {
					if(!isHome) {
						this.setTexture(owner.specBlockTexture);
					} else {
						this.setTexture(block7);
					}
				} else {
					this.setTexture(block7);
				}
			} else {
				if(owner == null) {
					this.setTexture(Resources.getInstance().planet2);
				} else {
					this.setTexture(planetTexture);
				}
			}
		}
		viewerNum += 1;
	}
	
	/**use for block alpha animation, call inside draw every time */
	public float getNextAlpha() {
		float temp = alpha[alphaPointer];
		if(alphaDelay == 0) {
			alphaPointer += 1;
			alphaDelay = 3;
		} else {
			alphaDelay -=1;
		}
		if(alphaPointer >= alpha.length) {
			alphaPointer = 0;
		}
		return temp;
	}
	
	@Override
	public void draw(Batch batch) {
		this.setSize(size, size);
		if(isConquerPoint && side != -1) {
			conquerPointRefreshTime -= Gdx.graphics.getDeltaTime();
			if(conquerPointRefreshTime <= 0) {
				GameInstance.getInstance().teamScores[side] += 5;
				conquerPointRefreshTime = 1f;
			}
			if(!containAEpack) {
				epackSpawnDelay -= Gdx.graphics.getDeltaTime();
				if(epackSpawnDelay <= 0) {
					spawnEpack();
					epackSpawnDelay = 5f;
				}
			}
		}
		
		float temp = getNextAlpha();
		if(isVisible && !isPlanet) {
			this.setColor(this.getColor().r, this.getColor().g, this.getColor().b, temp);
		}
		super.draw(batch);
		if(isConquerPoint && side != -1) {
			plus5.setAlpha(plusAlpha);
			plus5.setPosition(this.getX()+2, this.getY()+this.getHeight()/4+(1-plusAlpha)*10);
			plusAlpha = conquerPointRefreshTime;
			if(plusAlpha <= 0) {
				plusAlpha = 1;
			}
			if(isVisible) {
				plus5.draw(batch);
			}
		}
		if(containAEpack && isVisible) {
			epackContainHint.draw(batch);
		}
		
		if(currentEffect != null) {
			currentEffect.draw(batch);
			currentEffect.update(Gdx.graphics.getDeltaTime());
		}
				
	}
	
	public void spawnEpack() {
		containAEpack = true;
	}
	
	public void loseAEpack() {
		containAEpack = false;
	}
	
	public void dispose() {
		
	}
}
