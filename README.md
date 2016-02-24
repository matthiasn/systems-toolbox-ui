# systems-toolbox-ui

This library gives you all you need for building a user interface in the browser on top of **[React](https://facebook.github.io/react/)**, using ClojureScript and the **[system-toolbox](https://github.com/matthiasn/systems-toolbox)** library.

The general idea is that there is a **systems-toolbox** component or subsystem that holds application state and business logic. This component is then observed by UI components. The definition of the UI component is basically a function that transforms application state into a rendered user interface. Interaction with that state component then takes place via sending systems-toolbox style messages. UI components can also hold their own, independent state and interact with it directly, should there be a need for this.

One example of an application built this way is **[BirdWatch](https://github.com/matthiasn/Birdwatch)**.

This library has previously been part of the **[system-toolbox](https://github.com/matthiasn/systems-toolbox)** library and was moved into a separate repository to reduce dependencies.


## License

Copyright © 2015, 2016 Matthias Nehlsen

Distributed under the Eclipse Public License either version 1.0 or (at your option) any later version.
