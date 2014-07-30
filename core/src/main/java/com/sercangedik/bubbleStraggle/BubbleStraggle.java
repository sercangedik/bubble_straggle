package com.sercangedik.bubbleStraggle;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sercangedik.bubbleStraggle.managers.GameManager;

public class BubbleStraggle extends ApplicationAdapter {
	SpriteBatch batch;
	Box2DDebugRenderer debugRenderer;
	Texture wallTexture;
	Sprite wallSprite;
	private Viewport viewport;
	

	/*
	 * Ekran cizilmeden once olusturulan nesneler ve
	 * yuklenen ekranlar bu fonksiyonda bulunur.
	 * */
	@Override
	public void create() {
		batch = new SpriteBatch();
		
		//oyun sahnesi olusturulur
		GameManager.restart();
		
		//oyun sahnesine ait butun texture'lar bu fonksiyonda cekilir. (i/o)
		GameManager.getTextures();


	}

	/*
	 * Ekranin animasyonu esnasinda gerceklesen
	 * ve oyun sirasinda olan her sey bu fonksiyonda
	 * gerceklesir.
	 * 
	 * */
	@Override
	public void render() {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		//render oncesi dunyanin step'i ve zamani (delta) burada olusturulur.
		GameManager.beforeRender();

		//ekranin cizimi baslatilir
		batch.begin();
		
	    /* render esnasinda olan olaylar bu fonksiyonda cagirilir.
		 * ornek : klavyenin dinlenmesi, ekranin dinlenmesi, texture'larin cizilmesi gibi.
		 */
		GameManager.renderGame(batch);
		
	    /* oyuncunun saga ve sola hareketlerinde gerceklesen
		 * animasyonlar ve olaylar bu fonksiyonda kontrol edilir
		 */	
		GameManager.getPlayer().controlHandler(batch);
		
		/* oyuncunun ates etmesi esnasinda kontrol edilen carpisma durumu ve
		 * carpismadan sonra olacak olaylar bu fonksiyonda dinlenir.
		 */
		GameManager.getBullet().controlHandler(batch);
		
		//ekranin cizilmesi tamamlanir.
		batch.end();

		//saniye bari zamana gore yenilenir.
		GameManager.renderTimerBar();
	}

}
