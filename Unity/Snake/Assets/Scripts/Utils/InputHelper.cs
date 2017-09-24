using System.Collections;
using UnityEngine;

public class InputHelper : MonoBehaviour
{
	public static bool GetStandardMoveMultiInputKeys()
	{
		if(Input.GetKey(KeyCode.W) && Input.GetKey(KeyCode.A))
			return true;
		if(Input.GetKey(KeyCode.W) && Input.GetKey(KeyCode.S))
			return true;
		if(Input.GetKey(KeyCode.W) && Input.GetKey(KeyCode.D))
			return true;

		if(Input.GetKey(KeyCode.A) && Input.GetKey(KeyCode.S))
			return true;
		if(Input.GetKey(KeyCode.A) && Input.GetKey(KeyCode.D))
			return true;

		if(Input.GetKey(KeyCode.S) && Input.GetKey(KeyCode.D))
			return true;

		if(Input.GetKey(KeyCode.UpArrow) && Input.GetKey(KeyCode.LeftArrow))
			return true;
		if(Input.GetKey(KeyCode.UpArrow) && Input.GetKey(KeyCode.DownArrow))
			return true;
		if(Input.GetKey(KeyCode.UpArrow) && Input.GetKey(KeyCode.RightArrow))
			return true;

		if(Input.GetKey(KeyCode.LeftArrow) && Input.GetKey(KeyCode.DownArrow))
			return true;
		if(Input.GetKey(KeyCode.LeftArrow) && Input.GetKey(KeyCode.RightArrow))
			return true;

		if(Input.GetKey(KeyCode.DownArrow) && Input.GetKey(KeyCode.RightArrow))
			return true;

		return false;
	}

	public static bool GetStandardMoveUpDirection()
	{
        Debug.Log("Checking for UP");
        if (Input.GetKey (KeyCode.W) || Input.GetKey (KeyCode.UpArrow)) 
        {
            Debug.Log("Got UP");
            return true;
        }
		
		return false;
	}

	public static bool GetStandardMoveLeftDirection()
	{
        Debug.Log("Checking for LEFT");
        if(Input.GetKey(KeyCode.A) || Input.GetKey(KeyCode.LeftArrow))
			return true;
		
		return false;
	}

	public static bool GetStandardMoveDownDirection()
	{
        Debug.Log("Checking for DOWN");
        if(Input.GetKey(KeyCode.S) || Input.GetKey(KeyCode.DownArrow))
			return true;
		
		return false;
	}

	public static bool GetStandardMoveRightDirection()
	{
        Debug.Log("Checking for RIGHT");
        if(Input.GetKey(KeyCode.D) || Input.GetKey(KeyCode.RightArrow))
			return true;
		
		return false;
	}
}
