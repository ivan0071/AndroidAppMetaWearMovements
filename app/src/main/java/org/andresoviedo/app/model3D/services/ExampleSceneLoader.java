package org.andresoviedo.app.model3D.services;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.andresoviedo.app.model3D.model.AnimatedModel;
import org.andresoviedo.app.model3D.model.Object3DBuilder;
import org.andresoviedo.app.model3D.model.Object3DData;
import org.andresoviedo.app.model3D.services.collada.loader.ColladaLoader;
import org.andresoviedo.app.model3D.view.ModelActivity;
import org.apache.commons.io.IOUtils;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import javax.microedition.khronos.opengles.GL;

/**
 * This class loads a 3D scene as an example of what can be done with the app
 *
 * @author andresoviedo
 *
 */
public class ExampleSceneLoader extends SceneLoader {

	public Object3DData bodyRightHand = null;
	public Object3DData getBodyRightHand()
	{
		return bodyRightHand;
	}
	public void setBodyRightHand(float[] bodyRightHandLines)
	{
		bodyRightHand = Object3DBuilder.buildLine(bodyRightHandLines);
		return;
	}
	public float[] overwriteBodyRightHand = null;
	public void setOverwriteBodyRightHand(float[] overwriteBodyRightHandLines)
	{
        overwriteBodyRightHand = overwriteBodyRightHandLines;
	}


	public ExampleSceneLoader(ModelActivity modelActivity) {
		super(modelActivity);
	}

	public void init() {
		super.init();
		new AsyncTask<Void, Void, Void>() {

			ProgressDialog dialog = new ProgressDialog(parent);
			List<Exception> errors = new ArrayList<Exception>();

			@Override
			protected void onPreExecute() {
				super.onPreExecute();
				dialog.setCancelable(false);
				dialog.setMessage("Loading demo...");
				dialog.show();
			}

			@Override
			protected Void doInBackground(Void... params) {
				try {

					if(overwriteBodyRightHand == null){
						bodyRightHand = Object3DBuilder.buildLine(
								new float[] {
										0.0f, 1.5f, 0.5f, 0.1f, 1.15f, 0.5f,
										0.1f, 1.15f, 0.5f, 0.1f, 0.75f, 0.5f
								});
					} else {
						bodyRightHand = Object3DBuilder.buildLine(overwriteBodyRightHand);
					}
					bodyRightHand.setColor(new float[] { 1.0f, 1.0f, 1.0f, 1.0f });
					addObject(bodyRightHand);




String[] imageFilePaths = new String[] { "Form_3Dcuboid/Right.png", "Form_3Dcuboid/Left.png", "Form_3Dcuboid/Back.png", "Form_3Dcuboid/Front.png", "Form_3Dcuboid/Top.png", "Form_3Dcuboid/Bottom.png" };
float[] dimensions = new float[] { 6, 4, 2 };
String cameraView = "Front";
float cameraDistance = 50.0f;
boolean MinimizeInsteadOfClose = false;

float[] transformationMatrix = new float[]
		{1.0f, 0.0f, 0.0f, 0.0f,
		 0.0f, 1.0f, 0.0f, 0.0f,
		 0.0f, 0.0f, 1.0f, 0.0f,
		 0.0f, 0.0f, 0.0f, 1.0f};
String[] imageFiles = imageFilePaths;
float halfXdimension = dimensions[0] / 2;
float halfYdimension = dimensions[1] / 2;
float halfZdimension = dimensions[2] / 2;
float shiftX = 0.0f;
float shiftY = 0.0f;
float shiftZ = 0.0f;
float[] Quaternion = null;

Object3DData testObject = Object3DBuilder.buildCubeForSensor(halfXdimension, halfYdimension, halfZdimension, shiftX, shiftY, shiftZ, cameraView, Quaternion);
addObject(testObject);


					Object3DData bodyLeftHand = Object3DBuilder.buildLine(
						new float[] {
							0.0f, 1.5f, 0.5f, -0.1f, 1.15f, 0.5f,
							-0.1f, 1.15f, 0.5f, -0.1f, 0.75f, 0.5f
						});

					bodyLeftHand.setColor(new float[] { 1.0f, 1.0f, 1.0f, 1.0f });
					addObject(bodyLeftHand);

					// 3D Axis
					Object3DData axis = Object3DBuilder.buildAxis().setId("axis");
					axis.setColor(new float[] { 1.0f, 0, 0, 1.0f });
					addObject(axis);

					Object3DData bodyBase = Object3DBuilder.buildLine(
							new float[] { 0.0f, 0.75f, 0.5f, 0.0f, 1.7f, 0.5f });
					bodyBase.setColor(new float[] { 1.0f, 1.0f, 1.0f, 1.0f });
					addObject(bodyBase);

					Object3DData bodyLeggs = Object3DBuilder.buildLine(
							new float[] { 0.0f, 0.75f, 0.5f, -0.25f, 0.0f, 0.5f,
									0.0f, 0.75f, 0.5f, +0.25f, 0.0f, 0.5f });
					bodyLeggs.setColor(new float[] { 1.0f, 1.0f, 1.0f, 1.0f });
					addObject(bodyLeggs);

					Object3DData bodyHead = Object3DBuilder.buildLine(
							new float[] { 0.0f, 1.7f, 0.5f, 0.15f, 1.85f, 0.5f,
									0.15f, 1.85f, 0.5f, 0f, 2f, 0.5f,
									0f, 2f, 0.5f, -0.15f, 1.85f, 0.5f,
									-0.15f, 1.85f, 0.5f, 0.0f, 1.7f, 0.5f});
					bodyHead.setColor(new float[] { 1.0f, 1.0f, 1.0f, 1.0f });
					addObject(bodyHead);

/*
					// test cube made of arrays
					Object3DData obj10 = Object3DBuilder.buildCubeV1();
					obj10.setColor(new float[] { 1f, 0f, 0f, 0.5f });
					obj10.setPosition(new float[] { -2f, 2f, 0f });
					addObject(obj10);

					// test cube made of wires (I explode it to see the faces better)
					Object3DData obj11 = Object3DBuilder.buildCubeV1();
					obj11.setColor(new float[] { 1f, 1f, 0f, 0.5f });
					obj11.setPosition(new float[] { 0f, 2f, 0f });
					obj11.centerAndScaleAndExplode(1.0f, 1.5f);
					obj11.setId(obj11.getId() + "_exploded");
					addObject(obj11);

					// test cube made of wires (I explode it to see the faces better)
					Object3DData obj12 = Object3DBuilder.buildCubeV1_with_normals();
					obj12.setColor(new float[] { 1f, 0f, 0f, 1f });
					obj12.setPosition(new float[] { 0f, 0f, -2f });
					addObject(obj12);

					// test loading object
					try {
						// this has no color array
						Object3DData android = Object3DBuilder.loadV5(parent.getAssets(), "models", "android.obj");
						android.setPosition(new float[] { 0f, 0f, 0f });
						android.setColor(new float[] { 1.0f, 1.0f, 1.0f, 1.0f });
						addObject(android);
					} catch (Exception ex) {
						errors.add(ex);
					}

					// test cube made of indices
					Object3DData obj20 = Object3DBuilder.buildSquareV2();
					obj20.setColor(new float[] { 0f, 1f, 0, 0.5f });
					obj20.setPosition(new float[] { 2f, 2f, 0f });
					addObject(obj20);

					// test cube with texture
					InputStream open = null;
					try {
						open = parent.getAssets().open("models/penguin.bmp");
						ByteArrayOutputStream baos = new ByteArrayOutputStream();
						IOUtils.copy(open, baos);
						baos.close();

						Object3DData obj3 = Object3DBuilder.buildCubeV3(baos.toByteArray());
						obj3.setColor(new float[] { 1f, 1f, 1f, 1f });
						obj3.setPosition(new float[] { -2f, -2f, 0f });
						addObject(obj3);
					} catch (Exception ex) {
						errors.add(ex);
					} finally {
						if (open != null) {
							try {
								open.close();
							} catch (IOException ex) {
							}
						}
					}

					// test cube with texture & colors
					open = null;
					try {
						open = parent.getAssets().open("models/cube.bmp");
						ByteArrayOutputStream baos = new ByteArrayOutputStream();
						IOUtils.copy(open, baos);
						baos.close();

						Object3DData obj4 = Object3DBuilder.buildCubeV4(baos.toByteArray());
						obj4.setColor(new float[] { 1f, 1f, 1f, 1f });
						obj4.setPosition(new float[] { 0f, -2f, 0f });
						addObject(obj4);
					} catch (Exception ex) {
						errors.add(ex);
					} finally {
						if (open != null) {
							try {
								open.close();
							} catch (IOException ex) {
							}
						}
					}

					// test loading object
					try {
						// this has no color array
						Object3DData obj51 = Object3DBuilder.loadV5(parent.getAssets(), "models", "teapot.obj");
						obj51.setPosition(new float[] { -2f, 0f, 0f });
						obj51.setColor(new float[] { 1.0f, 1.0f, 0f, 1.0f });
						addObject(obj51);
					} catch (Exception ex) {
						errors.add(ex);
					}

					// test loading object with materials
					try {
						// this has color array
						Object3DData obj52 = Object3DBuilder.loadV5(parent.getAssets(), "models", "cube.obj");
						obj52.setPosition(new float[] { 2f, -2f, 0f });
						obj52.setColor(new float[] { 0.0f, 1.0f, 1f, 1.0f });
						addObject(obj52);
					} catch (Exception ex) {
						errors.add(ex);
					}

					// test loading object made of polygonal faces
					try {
						// this has heterogeneous faces
						Object3DData obj53 = Object3DBuilder.loadV5(parent.getAssets(), "models", "ToyPlane.obj");
						obj53.centerAndScale(2.0f);
						obj53.setPosition(new float[] { 2f, 0f, 0f });
						obj53.setColor(new float[] { 1.0f, 1.0f, 1f, 1.0f });
						// obj53.setDrawMode(GLES20.GL_TRIANGLE_FAN);
						addObject(obj53);
					} catch (Exception ex) {
						errors.add(ex);
					}

					// test loading object without normals
					try {
						// this has heterogeneous faces
						Object3DData obj = Object3DBuilder.loadV5(parent.getAssets(), "models", "cube4.obj");
						obj.setPosition(new float[] { 0f, 2f, -2f });
						obj.setColor(new float[] { 0.3f, 0.52f, 1f, 1.0f });
						addObject(obj);
					} catch (Exception ex) {
						errors.add(ex);
					}
*/
					// test loading collada object
					/*try {
						// this has heterogeneous faces
						Object3DData data = ColladaLoader.load(new URL("android://org.andresoviedo.dddmodel2/assets/models/cowboy.dae"));
						loadTexture(data, new URL("android://org.andresoviedo.dddmodel2/assets/models/cowboy.png"));
						data.centerAndScale(4,new float[]{0,0,2});
						data.setColor(new float[]{1,1,1,1});
						addObject(data);
					} catch (Exception ex) {
						errors.add(ex);
					}*/
				} catch (Exception ex) {
					errors.add(ex);
				}
				return null;
			}

			@Override
			protected void onPostExecute(Void result) {
				super.onPostExecute(result);
				if (dialog.isShowing()) {
					dialog.dismiss();
				}
				if (!errors.isEmpty()) {
					StringBuilder msg = new StringBuilder("There was a problem loading the data");
					for (Exception error : errors) {
						Log.e("Example", error.getMessage(), error);
						msg.append("\n" + error.getMessage());
					}
					Toast.makeText(parent.getApplicationContext(), msg, Toast.LENGTH_LONG).show();
				}
			}
		}.execute();
	}
}
