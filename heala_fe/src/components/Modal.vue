<template>
  <div class="modal-container">
    <transition name="modal">
      <div class="modal-mask">
        <div class="modal-wrapper">
          <div class="modal-container">
            <div class="modal-header">
              <slot name="header">
                기본 헤더
              </slot>
            </div>
            <div class="modal-body">
              <slot name="body">
                기본바디: ~~ 하시겠습니까?
              </slot>
            </div>
            <div class="modal-footer">
              <slot name="footer">
                <button class="modal-button modal-default-button" @click="$emit('yes')">
                  예
                </button>
                <button class="modal-button modal-important-button" @click="$emit('no')">
                  아니오
                </button>
              </slot>
            </div>
          </div>
        </div>
      </div>
    </transition>
  </div>
</template>
<script>

export default {
  name: 'Modal',
}
</script>
<style>
.modal-container {
  text-align: center;
}
.modal-mask {
  position: fixed;
  z-index: 9998;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, .5);
  display: table;
  transition: opacity .3s ease;
}
.modal-wrapper {
  display: table-cell;
  vertical-align: middle;
}
.modal-container {
    width: 200px;
    margin: 0px auto;
    padding: 10px 10px;
    background-color: #fff;
    border-radius: 2px;
    box-shadow: 0 2px 8px rgba(0, 0, 0, .33);
    transition: all .3s ease;
}
.modal-header {
    font-size: 12px;
    font-weight: bold;
}
.modal-body {
    font-size: 11px;
    margin: 10px 0px 20px 0px;
}
.modal-footer {
    font-size: 10px;
    height: 13px;
}
.modal-button {
    width: auto;
    height: auto;
    background: inherit;
    font-size: 10px;
    border: none;
    margin: auto 5px;
    float:right;
}
.modal-button:hover {
    cursor: pointer;
}
</style>