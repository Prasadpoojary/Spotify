import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './component/header/header.component';
import { FooterComponent } from './component/footer/footer.component';
import { SongsComponent } from './component/songs/songs.component';
import { ArtistsComponent } from './component/artists/artists.component';
import { PlayerComponent } from './component/player/player.component';
import { ArtistViewComponent } from './component/artist-view/artist-view.component';
import { AddSongComponent } from './component/add-song/add-song.component';
import { AuthenticationComponent } from './component/authentication/authentication.component';
import { FormsModule } from '@angular/forms';




@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    SongsComponent,
    ArtistsComponent,
    PlayerComponent,
    ArtistViewComponent,
    AddSongComponent,
    AuthenticationComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,

  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
