import './style.css';
import * as THREE from 'three';
import { OrbitControls } from 'three/examples/jsm/controls/OrbitControls';
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
const material = new THREE.MeshStandardMaterial({color: 9001631}); //removed wireframe:true; here to show how light reflects off standard material objects
const torus = new THREE.Mesh(geometry, material);

/* have to add our object to display it */
scene.add(torus);

// lights
const pointLight = new THREE.PointLight(0xffffff);
pointLight.position.set(5, 5, 5);

const ambientLight = new THREE.AmbientLight(0xffffff);

scene.add(pointLight, ambientLight); //removed ambientLight because of whitewash on shape

//helpers
// const lightHelper = new THREE.PointLightHelper(pointLight)

// const gridHelper = new THREE.GridHelper(200, 50);
// scene.add(lightHelper, gridHelper) 

//init orbit controls which allow movement with the cursor
//this wlil listen to dom events on the mouse and update the camera position
const controls = new OrbitControls(camera, renderer.domElement);

function addStar(){
  //creating new "star" object, size is .25 and ....
  const geometry = new THREE.SphereGeometry(0.25, 24, 24);
  // giving star objects a material, same as torus material above
  const material = new THREE.MeshStandardMaterial({color: 0xffffff})
  // actually creates the star object given geometry and material
  const star = new THREE.Mesh( geometry, material);
  // initializing array of blank x,y,z values to be given random positions
  const [x, y, z] = Array(3).fill().map(() => THREE.MathUtils.randFloatSpread(100));
  // setting star position to defaults
  star.position.set(x, y, z);
  // adding single star to the scene
  scene.add(star)
}
// initializing array of 200 star objects that will be randomly placed thru loop
Array(200).fill().forEach(addStar)
// created spaceTexture that holds the background image for project/site
// const spaceTexture = new THREE.TextureLoader().load('/images/spacebg1.jpg');
// setting background of page to space texture initialized above
// scene.background = spaceTexture;

function moveCamera(){
  // finds where the user is scrolling to, 
  //get boundngClientRect will give us the dimiensions  of the viiewport,
  // top property will show how far we are from the top of the apge
  const t = document.body.getBoundingClientRect().top;

  // here maybe add rotation for other objects so they move on scroll


  //
  camera.position.z = t * -0.01;
  camera.position.x = t * -0.0002;
  camera.position.y = t * -0.0002;

}

document.body.onscroll = moveCamera

function animate(){
  requestAnimationFrame(animate);
  
  torus.rotation.x += 0.01;
  torus.rotation.y += 0.005;
  torus.rotation.z += 0.01;
  
  //controls.update();

  renderer.render(scene, camera);
}

animate()