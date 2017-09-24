using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Bubble : MonoBehaviour 
{
    [SerializeField]
    private float _moveSpeed = 1f;

    void Start()
    {
        Reset ();
    }

	void Update () 
    {
        transform.Translate (Vector3.up * Time.deltaTime * _moveSpeed);
        if (transform.position.y > 10)
            Reset ();
	}

    void Reset()
    {
        float randomHeight = Random.Range (-8f, -18f);

        transform.position = new Vector3 (transform.position.x, randomHeight, transform.position.z);
    }

    void OnTriggerEnter2D(Collider2D temp)
    {
        if (TempIsTheFish (temp)) 
        {
            ScoreKeeper scoreKeeper = GameObject.FindObjectOfType<ScoreKeeper> ();
            scoreKeeper.IncrementScore ();
            Reset ();
        }
    }

    bool TempIsTheFish(Collider2D temp)
    {
        return (temp.GetComponent<Fish> () != null);
    }
}
