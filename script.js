const minutesDisplay = document.getElementById('minutes');
const secondsDisplay = document.getElementById('seconds');
const startBtn = document.getElementById('startBtn');
const pauseBtn = document.getElementById('pauseBtn');
const resetBtn = document.getElementById('resetBtn');
const statusText = document.getElementById('status');
const notificationSound = document.getElementById('notificationSound');

let totalSeconds = 25 * 60;
let remainingSeconds = totalSeconds;
let isRunning = false;
let intervalId = null;

const audioContext = new (window.AudioContext || window.webkitAudioContext)();

function updateDisplay() {
    const minutes = Math.floor(remainingSeconds / 60);
    const seconds = remainingSeconds % 60;

    minutesDisplay.textContent = String(minutes).padStart(2, '0');
    secondsDisplay.textContent = String(seconds).padStart(2, '0');
}

function playNotificationSound() {
    const now = audioContext.currentTime;
    const notes = [800, 600, 800];
    const duration = 0.3;

    notes.forEach((frequency, index) => {
        const osc = audioContext.createOscillator();
        const gain = audioContext.createGain();

        osc.connect(gain);
        gain.connect(audioContext.destination);

        osc.frequency.value = frequency;
        osc.type = 'sine';

        gain.gain.setValueAtTime(0.3, now + index * 0.35);
        gain.gain.exponentialRampToValueAtTime(0.01, now + index * 0.35 + duration);

        osc.start(now + index * 0.35);
        osc.stop(now + index * 0.35 + duration);
    });
}

function startTimer() {
    if (isRunning) return;

    isRunning = true;
    startBtn.disabled = true;
    pauseBtn.disabled = false;
    statusText.textContent = '计时中...';

    intervalId = setInterval(() => {
        remainingSeconds--;
        updateDisplay();

        if (remainingSeconds <= 0) {
            clearInterval(intervalId);
            isRunning = false;
            startBtn.disabled = false;
            pauseBtn.disabled = true;
            statusText.textContent = '时间到!';
            statusText.classList.add('completed');
            playNotificationSound();
        }
    }, 1000);
}

function pauseTimer() {
    if (!isRunning) return;

    isRunning = false;
    clearInterval(intervalId);
    startBtn.disabled = false;
    pauseBtn.disabled = true;
    statusText.textContent = '已暂停';
    statusText.classList.remove('completed');
}

function resetTimer() {
    clearInterval(intervalId);
    isRunning = false;
    remainingSeconds = totalSeconds;
    startBtn.disabled = false;
    pauseBtn.disabled = true;
    statusText.textContent = '准备就绪';
    statusText.classList.remove('completed');
    updateDisplay();
}

startBtn.addEventListener('click', startTimer);
pauseBtn.addEventListener('click', pauseTimer);
resetBtn.addEventListener('click', resetTimer);

updateDisplay();
