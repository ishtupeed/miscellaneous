using System.Collections;
using UnityEngine;

public class Food : MonoBehaviour 
{
	public Rect foodPos = new Rect(0, 0, 20, 20);

	private static Food instance = null;
	private static int[] initXPos = new int[]
	{22, 42, 62, 82, 102, 122, 142, 162, 182, 202, 222, 242, 262, 282, 302, 322, 342,
	362, 382, 402, 422, 442, 462, 482, 502, 522, 542, 562, 582, 602, 622, 642, 662,
	682, 702, 722, 742, 762, 782, 802, 822, 842, 862, 882, 902, 922, 942, 962, 982};
	private static int[] initYPos = new int[]
	{94, 114, 134, 154, 174, 194, 214, 234, 254, 274, 294, 314, 334, 354, 374,
	394, 414, 434, 454, 474, 494, 514, 434, 554, 574, 594, 614, 634, 654};
	private Texture2D foodTexture;
	private AudioClip foodPickup;

	public static Food Instance
	{
		get
		{
			if(instance == null)
				instance = new GameObject("Food").AddComponent<Food>();
			return instance;
		}
	}

	public void OnApplicationQuit()
	{
		DestroyInstance();
	}

	public void DestroyInstance()
	{
		print("Food Instance Destroyed");

		instance = null;
	}

	public void UpdateFood()
	{
		print("Food Updated");

		// audio.Play();

		int ranX = Random.Range(0, initXPos.Length);
		int ranY = Random.Range(0, initYPos.Length);

		foodPos = new Rect(initXPos[ranX], initYPos[ranY], 20, 20);
	}

	void OnGUI()
	{
		if(Food.Instance != null)
			GUI.DrawTexture(foodPos, foodTexture);
	}

	public void Initialize()
	{
		print("Food Initialized");

		// if(!gameObject.GetComponent<AudioSource>())
		// {
		// 	foodPickup = Resources.Load("Sounds/Food Pickup") as AudioClip;

		// 	gameObject.AddComponent<AudioSource>();

		// 	audio.playOnAwake = false;
		// 	audio.loop = false;
		// 	audio.clip = foodPickup;
		// }

		transform.position = Vector3.zero;
		transform.rotation = Quaternion.identity;
		transform.localScale = Vector3.one;

		foodTexture = TextureHelper.CreateTexture(20, 20, Color.red);

		int ranX = Random.Range(0, initXPos.Length);
		int ranY = Random.Range(0, initYPos.Length);

		foodPos = new Rect(initXPos[ranX], initYPos[ranY], 20, 20);
	}
}
