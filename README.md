○ Project setup instructions
  -> multi module setup with data / domain / ui / app / network separation
  -> added dependencies and plugins for compose / recyclerView / lifecycle viewmodel / Picasso etc.

○ Library list with justifications
  -> Jetpack Compose used for UI (declarative UI usage + AndroidView)
  -> Room for local db storage
  -> dependency injection using dagger/hilt

○ Architecture justification
  -> separation of concern + easy to add multiple features in the future.

○ Match score logic description
  -> using formaula for determining compatibility. (research suggests that marrying in the mid-to-late 20s or early 30s may offer advantages like greater marital satisfaction and lower divorce rates.)

○ Offline and error handling strategy
  -> functionality to add liked users to Db with primary key
  -> functionality to remove unliked users from Db with primary key

○ Reflection and hypothetical design constraint response
  -> would use pagination for better page limit handling
