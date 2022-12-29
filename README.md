
# Loula Music 🎙️

Loula Music is a sample Media Player app, built with [Jetpack Compose][compose], dynamic theming and full featured architecture.


## Screenshots

<img src="screenshot/loula-app.gif"/>


### Others
* Music service is built using Media 3 Exo player library
* Images are loaded using [Coil][coil] library.

## Architecture
The app is built in a Redux-style, where each UI 'screen' has its own [ViewModel][viewmodel], which exposes a single [StateFlow][stateflow] containing the entire view state. Each [ViewModel][viewmodel] is responsible for subscribing to any data streams required for the view, as well as exposing functions which allow the UI to send events.



## Data

### Music data

The music data is fetched from Deezer Api,. 

