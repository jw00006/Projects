import './style.css';
import * as THREE from 'three';
// import {OrbitControls} from 'three/examples/jam/controls/OrbitControls';
/* need three items to 3d render 
1. scene
2. camera
3. renderer
*/
/* scene is similar to a container that will hold all of our objects */
const scene = new THREE.Scene();

/* multiple camera options to choose from but perspective camera is similar to human eye
first parameter is fov (0-360) and second is aspect ratio 
third and fourth deal with view frustum which controls which objects are visible relative to the camera itself, 0.1, 1000 allows us to see basically everything */
const camera = new THREE.PerspectiveCamera(75, window.innerWidth/window.innerHeight, 0.1, 1000);

/* renderer that actually renders in the objects created */
const renderer = new THREE.WebGL1Renderer({
  canvas: document.querySelector('#bg'),
});

renderer.setPixelRatio(window.devicePixelRatio);
/* setting renderer to full size of the window */
renderer.setSize(window.innerWidth, window.innerHeight);
/* setting z position to something other than 0 so we can see our created objects */
camera.position.setZ(30);
camera.position.setX(-3);
/*  */
renderer.render(scene, camera);

/* need three items for geometry/objects...
1. create new item
2. give it a material, most require a light source but "basic" ones do not require light
3. create a mesh by combining the geometry with the material */
const geometry = new THREE.TorusGeometry(10, 3, 16, 100);
const material = new THREE.MeshBasicMaterial({color: 9001631, wireframe: true});
const torus = new THREE.Mesh(geometry, material);

/* have to add our object to display it */
scene.add(torus);

// lights
const pointLight = new THREE.PointLight(0xffffff);
pointLight.position.set(5, 5, 5);

//const ambientLight = new THREE.AmbientLight(0xffffff);
scene.add(pointLight); //removed ambientLight because of whitewash on shape

function animate(){
  requestAnimationFrame(animate);
  
  torus.rotation.x += 0.01;
  torus.rotation.y += 0.005;
  torus.rotation.z += 0.01;
  
  renderer.render(scene, camera);
}

animate()