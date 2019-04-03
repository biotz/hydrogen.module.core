# hydrogen.module.cljs for Duct

It implements two modules for [Duct](https://github.com/duct-framework/duct).
`hydrogen.module.cljs.core` brings in config for doing SPAs and `hydrogen.module.cljs.session` further enriches it
 to support OIDC-based session management.

## Installation

[![Clojars Project](https://img.shields.io/clojars/v/hydrogen/module.cljs.svg)](https://clojars.org/hydrogen/module.cljs)

To install, add the following to your project `:dependencies`:

    [hydrogen/module.cljs "0.1.1"]

## Usage

For SPA:
```edn
{:hydrogen.module.cljs/core {}}
```

For SPA with session management:
```edn
{:hydrogen.module.cljs/core {}
 :hydrogen.module.cljs/session {}}
```

## License

Copyright (c) Magnet S Coop 2019.

The source code for the library is subject to the terms of the Mozilla Public License, v. 2.0. If a copy of the MPL was not distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/.
