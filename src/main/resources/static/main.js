var EMPTY_HTML = '';

var handleCreateSongSubmitted = (e) => {
    console.log('creating song');

    e.preventDefault();

    var songData = {};
    var inputs = $('form#songForm :input').serializeArray().forEach(function(input) {
        songData[input.name] = input.value;
    });

    $.ajax({
        url:'/objectDB/songs',
        type:'POST',
        data: JSON.stringify(songData),
        contentType:'application/json',
        dataType:"json",
        success: (song) => {
            $('#createSongResult').html(JSON.stringify(song, null, 2));
            updateSongListInAlbumForm();
        }
    });
};


var updateSongListInAlbumForm = () => {
    $('#albumSongsSelect').html(EMPTY_HTML);

    $.get("/objectDB/songs", function(songs){
         songs.forEach((song) => {
            $('#albumSongsSelect').append('<option value="' + song.id + '">' + song.id + ': ' + song.name + '</option>');
         });
    });
};

var handleCreateSampleAcousticClicked = () => {
    console.log('creating sample acoustic song');
    $.post("/objectDB/songs/acoustic", function(acoustic){
        $('#createAcousticSongResult').html(JSON.stringify(acoustic, null, 2));
        updateSongListInAlbumForm();
    });
};

var handleCreateAlbumSubmitted = (e) => {
    console.log('creating album');

     e.preventDefault();

        var albumData = {
            songs:[]
        };

        var inputs = $('form#albumForm :input').serializeArray().forEach((input) => {
            if (input.name === 'year') {
                albumData[input.name] = input.value;
            } else if (input.name = 'songs[]') {
                albumData.songs.push(input.value);
            }
        });

        $.ajax({
            url:'/objectDB/albums',
            type:'POST',
            data: JSON.stringify(albumData),
            contentType:'application/json',
            dataType:"json",
            success: (album) => {
                $('#createAlbumResult').html(JSON.stringify(album, null, 2));
            }
        });
};

var listAlbums = () => {
    console.log('listing albums');

    $.get("/objectDB/albums", function(albums){
        $('#albumList').html(JSON.stringify(albums, null, 2));
    });
};

var countAlbums = () => {
    console.log('querying the number of albums');

    $.get("/objectDB/albums/count", function(count){
        $('#albumCount').html(JSON.stringify(count, null, 2));
    });
};


var setUp = () => {
    $('#songForm').submit(handleCreateSongSubmitted);
    $('#createSampleAcoustic').click(handleCreateSampleAcousticClicked);
    $('#albumForm').submit(handleCreateAlbumSubmitted)
    $('#listAlbums').click(listAlbums);
    $('#countAlbum').click(countAlbums);
    updateSongListInAlbumForm();
};


$(setUp);