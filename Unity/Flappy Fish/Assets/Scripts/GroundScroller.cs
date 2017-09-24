using System.Collections;
using UnityEngine;

public class GroundScroller : MonoBehaviour 
{
    [SerializeField]
    private float _scrollSpeed = 1f;

    void Update()
    {
        Vector2 currentTextureOffset = this.GetComponent<Renderer> ().material.GetTextureOffset ("_MainTex");
        float distanceToScrollLeft = Time.deltaTime * _scrollSpeed;
        float newTextureOffset_x = currentTextureOffset.x + distanceToScrollLeft;
        currentTextureOffset = new Vector2 (newTextureOffset_x, currentTextureOffset.y);
        this.GetComponent<Renderer> ().material.SetTextureOffset ("_MainTex", currentTextureOffset);

    }
}
