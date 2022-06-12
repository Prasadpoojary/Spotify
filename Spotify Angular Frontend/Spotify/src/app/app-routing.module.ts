import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddSongComponent } from './component/add-song/add-song.component';
import { ArtistViewComponent } from './component/artist-view/artist-view.component';
import { AuthenticationComponent } from './component/authentication/authentication.component';
import { SongsComponent } from './component/songs/songs.component';

const routes: Routes = [
  {
    path : "",
    component : SongsComponent
  },
  {
    path : "login",
    component : AuthenticationComponent
  },
  {
    path : "play/:id",
    component : SongsComponent
  },
  {
    path : "artist/:id",
    component : ArtistViewComponent
  },
  {
    path : "addsong",
    component : AddSongComponent
  },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
