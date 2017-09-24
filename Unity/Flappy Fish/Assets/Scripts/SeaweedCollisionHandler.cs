using System.Collections;
using UnityEngine;
using UnityEngine.SceneManagement;

public class SeaweedCollisionHandler : MonoBehaviour 
{
    void OnTriggerEnter2D(Collider2D temp)
    {
        SceneManager.LoadScene(0);
    }
}
