using System.Collections;
using UnityEngine;

public class PlayerControls : MonoBehaviour 
{
    public KeyCode moveUp = KeyCode.W;
    public KeyCode moveDown = KeyCode.S;
    public float speed = 10.0f;

    private Rigidbody2D rb2d;

    void Start()
    {
        rb2d = GetComponent<Rigidbody2D> ();
    }

    void Update()
    {
        if (Input.GetKey (moveUp)) 
        {
            Vector2 vel = rb2d.velocity;
            vel.y = speed;
            rb2d.velocity = vel;
        } 
        else if (Input.GetKey (moveDown)) 
        {
            Vector2 vel = rb2d.velocity;
            vel.y = -1 * speed;
            rb2d.velocity = vel;
        }
        else if (!Input.anyKey) {
            Vector2 vel = rb2d.velocity;
            vel.y = 0.0f;
            rb2d.velocity = vel;
        }
        Vector2 reset = rb2d.velocity;
        reset.x = 0;
        rb2d.velocity = reset;
    }
}
