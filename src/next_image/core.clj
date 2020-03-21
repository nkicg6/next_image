(ns next-image.core
  (:require [seesaw.core :as seesaw]))

;; left off on line 182 of https://gist.github.com/daveray/1441520 where it starts to get useful
;; https://imagej.net/Tips_for_developers#Tips_for_Graphical_User_Interface_.28GUI.29_programming
;; see listbox example https://github.com/daveray/seesaw/blob/master/test/seesaw/test/examples/scroll.clj
;; seesaw api http://daveray.github.io/seesaw/seesaw.core-api.html
;; https://github.com/daveray/seesaw/blob/master/test/seesaw/test/examples/temp.clj

(seesaw/native!)

(def next-image-state
  "holds all state for app"
  (atom {:todo-files nil
         :done-files nil
         :todo-files-count nil
         :done-files-count nil}))

(defn update-todo-done-field!
  "updates todo and done lists in state atom.
  Also updates file lengths metadata"
  [state-atom k thing]
  (swap! state-atom assoc k thing)
  (swap! state-atom assoc :todo-files-count (count (:todo-files @state-atom)))
  (swap! state-atom assoc :done-files-count (count (:done-files @state-atom))))


(defn update-field!
  [state-atom k alt-msg]
  (if (= nil (k @state-atom))
    [alt-msg]
         (k @state-atom)))

(defn file-list
  []
  (seesaw/border-panel :north
                       (seesaw/scrollable
                        (seesaw/listbox :model ["Nothing here yet"]
                                        :handler ))
                       :south (seesaw/label "metadata here")))

(def next_image_gui
  (seesaw/frame :title "next image v0.1-dev"
                :width 400
                :height 600
                :content (file-list)
                :id "main"))

(defn start-app
  [gui]
  (-> gui
      seesaw/show!))

(defn mod [content]
  (seesaw/config! next_image_gui
                  :content content)
  content)

#_(start-app next_image_gui)

#_(mod (seesaw/label "new stuff"))
#_(seesaw/dispose! (seesaw/all-frames))

#_(update-todo-done-field! next-image-state :todo-files ["test1" "test2"])

