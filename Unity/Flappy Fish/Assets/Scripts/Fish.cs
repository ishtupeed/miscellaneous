using System.Collections;
using UnityEngine;
using UnityEngine.SceneManagement;

public class Fish : MonoBehaviour 
{
    [SerializeField]
    private float _upwardForceMultiplier = 200f;

	void Start () 
    {
		
	}
	
	void Update () 
    {
        bool pressedFireButton = Input.GetButtonDown ("Fire1");
        bool pressedSpaceButton = Input.GetKeyDown ("space");
        if (pressedFireButton || pressedSpaceButton) 
        {
            Rigidbody2D rgbd2d = GetComponent<Rigidbody2D> ();
            rgbd2d.velocity = Vector3.zero;
            rgbd2d.AddForce (Vector3.up * _upwardForceMultiplier);
        }
        if(transform.position.y > 6f || transform.position.y < - 6f)
            SceneManager.LoadScene(0);
	}
}
