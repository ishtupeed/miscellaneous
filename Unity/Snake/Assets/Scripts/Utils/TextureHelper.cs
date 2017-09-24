using System.Collections;
using UnityEngine;

public class TextureHelper : MonoBehaviour 
{
	public static Texture2D CreateBlackTexture(int width, int height)
	{
		return CreateTexture(width, height, Color.black);
	}

	public static Texture2D CreateTexture(int width, int height, Color color)
	{
		Texture2D texture = new Texture2D(width, height);

		for(int i = 0; i < width; i++)
			for(int j = 0; j < height; j++)
				texture.SetPixel(i, j, color);

		texture.Apply();

		return texture;
	}

	public static Texture2D Create1x1BlackTexture()
	{
		return Create1x1Texture(Color.black);
	}

	public static Texture2D Create1x1Texture(Color color)
	{
		return CreateTexture(1, 1, color);
	}
}
