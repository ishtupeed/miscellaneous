using System.Collections;
using UnityEngine;

public class MoveLeft : MonoBehaviour 
{
    [SerializeField]
    private float _speed = 2.5f;
    [SerializeField]
    private bool _randomizeHeight = true;

	void Update () 
    {
        transform.Translate (Vector3.left * Time.deltaTime * _speed);
        if (transform.position.x < -15) 
        {
            if (_randomizeHeight) {
                float randomYPosition = UnityEngine.Random.Range (-3, 3);
                transform.position = new Vector3 (15, randomYPosition, 0);
            }
            else
                transform.position = new Vector3 (15, transform.position.y, 0);
        }

	}
}
