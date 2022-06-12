import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-add-song',
  templateUrl: './add-song.component.html',
  styleUrls: ['./add-song.component.css']
})
export class AddSongComponent implements OnInit {

  showAddSongForm:boolean = true;
  constructor() { }

  ngOnInit(): void {
  }

  toggleAddSongForm=()=>
  {
       this.showAddSongForm=!this.showAddSongForm;
  }

  onAddSong=(songForm:NgForm)=>
  {

  }

  onAddArtist=(artistForm:NgForm)=>
  {

  }




}
