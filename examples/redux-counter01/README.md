# systems-toolbox-ui - Redux-style counter example

## Usage

You can start the server side application as usual:

    $ lein run

Note that in this step, it does not do anything other than serve a client-side application. This will run the application on **[http://localhost:8888/](http://localhost:8888/)**. However, we will still need to compile the ClojureScript:

    $ lein cljsbuild auto release

This will compile the ClojureScript into JavaScript using `:advanced` optimization.


## License

Distributed under the Eclipse Public License either version 1.0 or (at your option) any later version.
